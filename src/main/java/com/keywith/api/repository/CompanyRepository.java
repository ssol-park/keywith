package com.keywith.api.repository;

import com.keywith.api.entity.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends ReactiveCrudRepository<Company, Integer> {
}
