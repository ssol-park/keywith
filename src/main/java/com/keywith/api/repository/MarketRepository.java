package com.keywith.api.repository;

import com.keywith.api.entity.Market;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends ReactiveCrudRepository<Market, Long> {
}
