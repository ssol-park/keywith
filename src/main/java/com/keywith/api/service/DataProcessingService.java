package com.keywith.api.service;

import com.keywith.api.dto.ScrapResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DataProcessingService {

    private final CompanyService companyService;
    private final PublicOfferingService publicOfferingService;

    public DataProcessingService(CompanyService companyService, PublicOfferingService publicOfferingService) {
        this.companyService = companyService;
        this.publicOfferingService = publicOfferingService;
    }

    public void processAndSaveData(ScrapResultDto scrapDto) {
        companyService.saveCompany(scrapDto)
                .then(publicOfferingService.savePublicOffering(scrapDto))
                .subscribe(
                        result -> log.info("Data processed and saved successfully: {}", result),
                        error -> log.error("Error processing and saving data", error)
                );
    }
}
