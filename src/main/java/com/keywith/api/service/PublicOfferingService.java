package com.keywith.api.service;

import com.keywith.api.dto.ScrapResultDto;
import com.keywith.api.entity.PublicOffering;
import com.keywith.api.entity.PublicOfferingUnderwriter;
import com.keywith.api.entity.Underwriter;
import com.keywith.api.repository.PublicOfferingRepository;
import com.keywith.api.repository.PublicOfferingUnderwriterRepository;
import com.keywith.api.repository.UnderwriterRepository;
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
    private final PublicOfferingUnderwriterRepository publicOfferingUnderwriterRepository;

    public PublicOfferingService(PublicOfferingRepository publicOfferingRepository, UnderwriterRepository underwriterRepository, PublicOfferingUnderwriterRepository publicOfferingUnderwriterRepository) {
        this.publicOfferingRepository = publicOfferingRepository;
        this.underwriterRepository = underwriterRepository;
        this.publicOfferingUnderwriterRepository = publicOfferingUnderwriterRepository;
    }
    public Mono<PublicOffering> savePublicOffering(ScrapResultDto scrapDto) {
        return publicOfferingRepository.save(new PublicOffering(scrapDto))
                .flatMap(savedOffering -> saveUnderwriters(savedOffering, scrapDto.getUnderwriters()));
    }

    private Mono<PublicOffering> saveUnderwriters(PublicOffering savedOffering, List<String> underwriters) {
        return Flux.fromIterable(underwriters)
                .flatMap(this::findOrCreateUnderwriter)
                .flatMap(underwriter -> savePublicOfferingUnderwriter(savedOffering.getId(), underwriter.getId()))
                .then(Mono.just(savedOffering));
    }

    private Mono<Underwriter> findOrCreateUnderwriter(String name) {
        return underwriterRepository.findByName(name)
                .switchIfEmpty(createUnderwriter(name));
    }

    private Mono<Underwriter> createUnderwriter(String name) {
        return underwriterRepository.save(new Underwriter(name));
    }

    private Mono<PublicOfferingUnderwriter> savePublicOfferingUnderwriter(Long offeringId, Long underwriterId) {
        return publicOfferingUnderwriterRepository.save(new PublicOfferingUnderwriter(offeringId, underwriterId));
    }
}
