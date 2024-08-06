package com.keywith.api.entity;

import com.keywith.api.dto.ScrapResultDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@Table("public_offering")
public class PublicOffering {
    @Id
    private long id;
    private int stockCode;
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

    public PublicOffering(ScrapResultDto scrapDto) {
        this.stockCode = scrapDto.getStockCode();
        this.offeringScheduleStart = scrapDto.getOfferingScheduleStart();
        this.offeringScheduleEnd = scrapDto.getOfferingScheduleEnd();
        this.confirmedOfferingPrice = scrapDto.getConfirmedOfferingPrice();
        this.desiredOfferingPriceMin = scrapDto.getDesiredOfferingPriceMin();
        this.desiredOfferingPriceMax = scrapDto.getDesiredOfferingPriceMax();
        this.subscriptionCompetitionRate = scrapDto.getSubscriptionCompetitionRate();
        this.subscriptionDate = scrapDto.getListingDate();
        this.paymentDate = scrapDto.getPaymentDate();
        this.refundDate = scrapDto.getRefundDate();
    }

}
