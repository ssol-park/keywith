package com.keywith.api.service;

import com.keywith.api.dto.ScrapResultDto;
import com.keywith.api.entity.PublicOffering;
import com.keywith.api.entity.PublicOfferingUnderwriter;
import com.keywith.api.entity.Underwriter;
import com.keywith.api.mapper.PublicOfferingMapper;
import com.keywith.api.query.SQLQueries;
import com.keywith.api.repository.PublicOfferingRepository;
import com.keywith.api.repository.UnderwriterRepository;
import com.keywith.api.utils.QueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class PublicOfferingService {
    private final PublicOfferingRepository publicOfferingRepository;
    private final UnderwriterRepository underwriterRepository;
    private final QueryHelper queryHelper;
    private final PublicOfferingMapper publicOfferingMapper;

    public PublicOfferingService(PublicOfferingRepository publicOfferingRepository, UnderwriterRepository underwriterRepository, QueryHelper queryHelper, PublicOfferingMapper publicOfferingMapper) {
        this.publicOfferingRepository = publicOfferingRepository;
        this.underwriterRepository = underwriterRepository;
        this.queryHelper = queryHelper;
        this.publicOfferingMapper = publicOfferingMapper;
    }

    public Mono<Void> savePublicOffering(ScrapResultDto scrapDto) {
        PublicOffering publicOffering = publicOfferingMapper.scrapDtoToPublicOffering(scrapDto);

        return queryHelper.executeUpsert(SQLQueries.PublicOffering.UPSERT, publicOffering)
                .then(publicOfferingRepository.findByStockCode(publicOffering.getStockCode()))
                .flatMap(savedOffering -> saveUnderwriters(savedOffering, scrapDto.getUnderwriters()).then())
                ;
    }

    private Flux<Void> saveUnderwriters(PublicOffering savedOffering, List<String> underwriters) {
        return Flux.fromIterable(underwriters)
                .flatMap(this::findOrCreateUnderwriter)
                .flatMap(underwriter -> {
                    PublicOfferingUnderwriter publicOfferingUnderwriter = publicOfferingMapper.mapToPublicOfferingUnderwriter(savedOffering, underwriter);
                    return queryHelper.executeUpsert(SQLQueries.PublicOfferingUnderwriter.UPSERT, publicOfferingUnderwriter);
                });
    }

    private Mono<Underwriter> findOrCreateUnderwriter(String name) {
        return underwriterRepository.findByName(name)
                .switchIfEmpty(createUnderwriter(name));
    }

    private Mono<Underwriter> createUnderwriter(String name) {
        return underwriterRepository.save(new Underwriter(name));
    }
}
