package com.keywith.api;

import com.keywith.api.entity.Company;
import com.keywith.api.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;

@DataR2dbcTest
//@ActiveProfiles("") 추후 profile 설정시
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testFindAll() {
        Flux<Company> allCompanies = companyRepository.findAll();
        allCompanies.subscribe(
                result -> System.out.println("result = " + result),
                error -> System.err.println("error = " + error)
        );

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
