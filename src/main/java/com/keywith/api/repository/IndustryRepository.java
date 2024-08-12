package com.keywith.api.repository;

import com.keywith.api.entity.Industry;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IndustryRepository extends ReactiveCrudRepository<Industry, Long> {
    Mono<Industry> findByName(String name);
}
