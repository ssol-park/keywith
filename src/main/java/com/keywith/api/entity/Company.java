package com.keywith.api.entity;

import com.keywith.api.dto.ScrapResultDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
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
    private String listingDate;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Company(ScrapResultDto scrapData, Industry industry, Market market) {
        this.stockCode = scrapData.getStockCode();
        this.companyName = scrapData.getStockName();
        this.industryId = industry.getId();
        this.marketId = market.getId();
        this.revenue = scrapData.getRevenue();
        this.netIncome = scrapData.getNetIncome();
        this.website = scrapData.getWebsite();
        this.listingDate = scrapData.getListingDate();
    }

    public void setStockCode(int stockCode) {
        this.stockCode = stockCode;
    }
}
