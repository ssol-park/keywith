package com.keywith.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keywith.api.dto.ScrapResultDto;
import com.keywith.api.service.DataProcessingService;
import com.keywith.api.service.ScrapService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

@SpringBootTest
class ScrapServiceTest {
    private static final Logger log = LoggerFactory.getLogger(ScrapServiceTest.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ScrapService scrapService;

    @Autowired
    DataProcessingService dataProcessingService;

    @Test
    @DisplayName("스크래핑 및 json 매핑 테스트")
    void testScrapData() throws IOException {
        Flux<ScrapResultDto> scrapResultFlux = scrapService.scrapData();

        StepVerifier.create(scrapResultFlux)
                .thenConsumeWhile(data -> {
                    try {
                        log.info("Result: {}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                    return !ObjectUtils.isEmpty(data);
                })
                .expectComplete()
                .verify();

        log.info("testScrapData completed.");
    }

    @Test
    @DisplayName("스크래핑 및 데이터 저장 테스트")
    void testScrapDataAndSaveData() throws IOException {
        Flux<ScrapResultDto> scrapResultFlux = scrapService.scrapData();


        StepVerifier.create(scrapResultFlux.flatMap(scrapDto ->
                        dataProcessingService.processAndSaveData(scrapDto)
                ))
                .expectComplete()
                .verify();
    }
}
