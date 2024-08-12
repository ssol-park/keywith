package com.keywith.api.service;

import com.keywith.api.dto.ScrapResultDto;
import com.keywith.api.entity.Company;
import com.keywith.api.entity.Industry;
import com.keywith.api.entity.Market;
import com.keywith.api.repository.CompanyRepository;
import com.keywith.api.repository.IndustryRepository;
import com.keywith.api.repository.MarketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final MarketRepository marketRepository;
    private final IndustryRepository industryRepository;

    public CompanyService(CompanyRepository companyRepository, MarketRepository marketRepository, IndustryRepository industryRepository) {
        this.companyRepository = companyRepository;
        this.marketRepository = marketRepository;
        this.industryRepository = industryRepository;
    }

    public Mono<Company> saveCompany(ScrapResultDto scrapDto) {
        return Mono.zip(
                findOrCreateIndustry(scrapDto.getIndustry()),
                findOrCreateMarket(scrapDto.getMarketType())
        ).flatMap(tuple -> {
            Industry industry = tuple.getT1();
            Market market = tuple.getT2();

            return companyRepository.save(new Company(scrapDto, industry, market));
        });
    }

    private Mono<Industry> findOrCreateIndustry(String name) {
        return industryRepository.findByName(name)
                .switchIfEmpty(createIndustry(name));
    }

    private Mono<Industry> createIndustry(String name) {
        return industryRepository.save(new Industry(name));
    }

    private Mono<Market> findOrCreateMarket(String name) {
        return marketRepository.findByName(name)
                .switchIfEmpty(createMarket(name));
    }

    private Mono<Market> createMarket(String name) {
        return marketRepository.save(new Market(name));
    }
}
