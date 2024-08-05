package com.keywith.api.repository;

import com.keywith.api.entity.Industry;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustryRepository extends ReactiveCrudRepository<Industry, Long> {
}
