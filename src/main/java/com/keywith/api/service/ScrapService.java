package com.keywith.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keywith.api.JsoupUtil;
import com.keywith.api.KeywordConverter;
import com.keywith.api.ScrapProperties;
import com.keywith.api.ScrapingSections;
import com.keywith.api.dto.ScrapResultDto;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ScrapService {

    private ObjectMapper om = new ObjectMapper();
    private final ScrapProperties scrapProperties;

    public ScrapService(ScrapProperties scrapProperties) {
        this.scrapProperties = scrapProperties;
    }

    public Flux<ScrapResultDto> scrapData() throws IOException {
        List<String> detailPageUrls = getDetailPageUrls(scrapProperties.getBaseUrl(), ScrapingSections.CALENDER_INFO);

         return Flux.fromIterable(detailPageUrls)
                .parallel()
                .runOn(Schedulers.boundedElastic())
                .flatMap(this::scrapDetailPage)
                .map(data -> om.convertValue(data, ScrapResultDto.class))
                .sequential();
    }

    private Mono<Map<String, Object>> scrapDetailPage(String detailUrl) {
        return Mono.fromCallable(() -> {
            Document document = JsoupUtil.getDocumentByUrl(detailUrl);

            return Stream.of(
                        ScrapingSections.COMPANY_INFO,
                        ScrapingSections.STOCK_INFO,
                        ScrapingSections.SUBSCRIPTION_INFO
                    )
                    .map(section -> getDataBySection(document, section))
                    .flatMap(map -> map.entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> newValue
                    ));
        }).onErrorResume(e -> {
            e.printStackTrace();
            log.error("ERROR :: {}", e.getMessage());
            return Mono.empty();
        });

    }

    public List<String> getDetailPageUrls(String url, ScrapingSections section) throws IOException {
        Document document = JsoupUtil.getDocumentByUrl(url);

        Element tbody = JsoupUtil.getTbodyBySummaryAndSection(document, section);

        return tbody.select("tr").stream()
                .flatMap(tr -> tr.select("a").stream())
                .filter(a -> containsKey(a.text(), section.getKeywords()))
                .map(a -> scrapProperties.getDomain() + a.attr("href"))
                .distinct()
                .toList();
    }

    public Map<String, Object> getDataBySection(Document document, ScrapingSections section) {
        return JsoupUtil.getTbodyBySummaryAndSection(document, section)
                .select("tr").stream()
                .map(tr -> getTargetDataMap(section, tr))
                .filter(map -> !ObjectUtils.isEmpty(map))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (existing, replacement) -> existing + ", " + replacement  // 병합 로직
                ));
    }

    public Map<String, Object> getTargetDataMap(ScrapingSections section, Element tr) {
        Map<String, Object> scrapMap = new HashMap<>();

        Elements tds = tr.select("td");

        for (int i = 0; i < tds.size(); i++) {
            String key = tds.get(i).text();

            if(section.getKeywords().contains(key)) {
                KeywordConverter.convert(key, tds.get(i + 1).text())
                        .entrySet()
                        .forEach(entry -> scrapMap.put(entry.getKey(), entry.getValue()));
            }
        }

        return scrapMap;
    }

    private static boolean containsKey(String str, List<String> keywords) {
        for (String keyword : keywords) {
            if (str.contains(keyword))
                return true;
        }

        return false;
    }
}
