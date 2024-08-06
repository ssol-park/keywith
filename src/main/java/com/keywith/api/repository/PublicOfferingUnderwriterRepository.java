package com.keywith.api.repository;

import com.keywith.api.entity.PublicOfferingUnderwriter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicOfferingUnderwriterRepository extends ReactiveCrudRepository<PublicOfferingUnderwriter, Long> {
}
