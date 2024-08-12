package com.keywith.api.repository;

import com.keywith.api.entity.Market;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MarketRepository extends ReactiveCrudRepository<Market, Long> {
    Mono<Market> findByName(String name);
}
