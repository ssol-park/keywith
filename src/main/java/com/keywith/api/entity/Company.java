package com.keywith.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table("company")
public class Company {
    @Id
    private int stockCode;
    private String companyName;
    private int marketId;
    private int industryId;
    private long revenue;
    private long netIncome;
    private String website;
    private LocalDateTime listingDate;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
