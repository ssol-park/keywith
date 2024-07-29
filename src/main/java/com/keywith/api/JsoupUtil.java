package com.keywith.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsoupUtil {

    public static Element getTbodyBySummaryAndSection(Document document, ScrapingSections section) {
        Element table = getTableBySummary(document, section.getSummary());
        return Optional.ofNullable(table.selectFirst("tbody")).orElseThrow(() -> new RuntimeException("empty tbody"));
    }

    public static Element getTableBySummary(Document document, String summary) {
        return Optional.ofNullable(document.selectFirst(String.format("table[summary='%s']", summary))).orElseThrow(() -> new RuntimeException("empty table"));
    }

    public static Document getDocumentByUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
