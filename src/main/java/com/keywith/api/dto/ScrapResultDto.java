package com.keywith.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class ScrapResultDto {
    private int stockCode;
    private String stockName;
    private String listingDate;
    private String website;
    private String offeringScheduleEnd;
    private String industry;
    private long desiredOfferingPriceMax;
    private String marketType;
    private long revenue;
    private String offeringScheduleStart;
    private String subscriptionCompetitionRate;
    private long confirmedOfferingPrice;
    private long netIncome;
    private String paymentDate;
    private long desiredOfferingPriceMin;
    private String refundDate;
    private List<String> underwriters;
}
