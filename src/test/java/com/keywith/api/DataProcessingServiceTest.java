package com.keywith.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keywith.api.dto.ScrapResultDto;
import com.keywith.api.service.DataProcessingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DataProcessingServiceTest {

    private static final Logger log = LoggerFactory.getLogger(DataProcessingServiceTest.class);
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private DataProcessingService dataProcessingService;

    private List<ScrapResultDto> sampleData;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = "[{\n" +
                "  \"stockCode\" : 476080,\n" +
                "  \"stockName\" : \"엠83\",\n" +
                "  \"listingDate\" : \"2024.08.22\",\n" +
                "  \"website\" : \"m83.co.kr\",\n" +
                "  \"industry\" : \"일반 영화 및 비디오물 제작업\",\n" +
                "  \"marketType\" : \"코스닥\",\n" +
                "  \"competitionRate\" : \"638.05:1 (비례 1276:1)\",\n" +
                "  \"offeringStartDate\" : \"2024.08.12\",\n" +
                "  \"offeringEndDate\" : \"2024.08.13\",\n" +
                "  \"desiredPriceMin\" : 0,\n" +
                "  \"desiredPriceMax\" : 0,\n" +
                "  \"confirmedPrice\" : 16000,\n" +
                "  \"paymentDate\" : \"2024.08.16\",\n" +
                "  \"refundDate\" : \"2024.08.16\",\n" +
                "  \"revenue\" : 42113000000,\n" +
                "  \"netIncome\" : 5682000000,\n" +
                "  \"underwriters\" : \"신영증권,유진투자증권\"" +
                "}]";
        sampleData = objectMapper.readValue(jsonData, objectMapper.getTypeFactory().constructCollectionType(List.class, ScrapResultDto.class));
    }

    @Test
    void testProcessAndSaveData() {
        sampleData.forEach(dataProcessingService::processAndSaveData);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
