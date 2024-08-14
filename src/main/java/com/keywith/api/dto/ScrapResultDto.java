package com.keywith.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
public class ScrapResultDto {
    private int stockCode;
    private String stockName;
    private String listingDate;
    private String website;
    private String industry;
    private String marketType;
    private String competitionRate;
    private String offeringStartDate;
    private String offeringEndDate;
    private long desiredPriceMin;
    private long desiredPriceMax;
    private long confirmedPrice;
    private String paymentDate;
    private String refundDate;
    private long revenue;
    private long netIncome;
    private List<String> underwriters;

    @JsonCreator
    public ScrapResultDto(@JsonProperty("underwriters") String underwriters) {
        this.underwriters = Arrays.asList(underwriters.split(","));
    }
}
