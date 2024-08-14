package com.keywith.api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table("company")
public class Company {
    @Id
    private int stockCode;
    private String companyName;
    private long marketId;
    private long industryId;
    private long revenue;
    private long netIncome;
    private String website;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
