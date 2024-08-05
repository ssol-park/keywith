package com.keywith.api.repository;

import com.keywith.api.entity.PublicOffering;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicOfferingRepository extends ReactiveCrudRepository<PublicOffering, Long> {
}
