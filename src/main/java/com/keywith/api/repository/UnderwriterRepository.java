package com.keywith.api.repository;

import com.keywith.api.entity.Underwriter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UnderwriterRepository extends ReactiveCrudRepository<Underwriter, Long> {
    Mono<Underwriter> findByName(String name);
}
