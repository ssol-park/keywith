package com.keywith.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Table("public_offering")
public class PublicOffering {
    @Id
    private long id;
    private int stockCode;
    private int underwriterId;
    private String offeringScheduleStart;
    private String offeringScheduleEnd;
    private long confirmedOfferingPrice;
    private long desiredOfferingPriceMin;
    private long desiredOfferingPriceMax;
    private String subscriptionCompetitionRate;
    private String subscriptionDate;
    private String paymentDate;
    private String refundDate;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
