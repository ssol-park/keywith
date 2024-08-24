package com.keywith.api.service;

import com.keywith.api.dto.ScrapResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DataProcessingService {

    private final CompanyService companyService;
    private final PublicOfferingService publicOfferingService;

    public DataProcessingService(CompanyService companyService, PublicOfferingService publicOfferingService) {
        this.companyService = companyService;
        this.publicOfferingService = publicOfferingService;
    }

    public Mono<Void> processAndSaveData(ScrapResultDto scrapDto) {
        return companyService.saveCompany(scrapDto)
                .then(publicOfferingService.savePublicOffering(scrapDto))
                .doOnError(error -> {
                    log.error("[processAndSaveData] Failed to save data for ScrapResultDto {}: {}", scrapDto, error.getMessage(), error);
                })
                .then();
    }
}
