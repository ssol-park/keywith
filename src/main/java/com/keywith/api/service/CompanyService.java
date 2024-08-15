package com.keywith.api.service;

import com.keywith.api.dto.ScrapResultDto;
import com.keywith.api.entity.Company;
import com.keywith.api.entity.Industry;
import com.keywith.api.entity.Market;
import com.keywith.api.mapper.CompanyMapper;
import com.keywith.api.query.SQLQueries;
import com.keywith.api.repository.IndustryRepository;
import com.keywith.api.repository.MarketRepository;
import com.keywith.api.utils.QueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CompanyService {
    private final MarketRepository marketRepository;
    private final IndustryRepository industryRepository;
    private final QueryHelper queryHelper;
    private final CompanyMapper companyMapper;

    public CompanyService(MarketRepository marketRepository, IndustryRepository industryRepository, QueryHelper queryHelper, CompanyMapper companyMapper) {
        this.marketRepository = marketRepository;
        this.industryRepository = industryRepository;
        this.queryHelper = queryHelper;
        this.companyMapper = companyMapper;
    }

    public Mono<Void> saveCompany(ScrapResultDto scrapDto) {
        return Mono.zip(
                findOrCreateIndustry(scrapDto.getIndustry()),
                findOrCreateMarket(scrapDto.getMarketType())
        ).flatMap(tuple -> {
            Industry industry = tuple.getT1();
            Market market = tuple.getT2();
            Company company = companyMapper.scrapDtoToCompany(scrapDto, industry, market);

            return queryHelper.executeUpsert(SQLQueries.Company.UPSERT, company);
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
