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
@Table("public_offering")
public class PublicOffering {
    @Id
    private Long id;
    private int stockCode;
    private String offeringStartDate;
    private String offeringEndDate;
    private long confirmedPrice;
    private long desiredPriceMin;
    private long desiredPriceMax;
    private String competitionRate;
    private String listingDate;
    private String paymentDate;
    private String refundDate;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
