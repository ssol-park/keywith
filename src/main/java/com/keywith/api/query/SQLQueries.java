package com.keywith.api.query;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SQLQueries {

    public static class Company {
        public static final String UPSERT = "INSERT INTO company (stock_code, company_name, market_id, industry_id, revenue, net_income, website) " +
                "VALUES (:stockCode, :companyName, :marketId, :industryId, :revenue, :netIncome, :website) " +
                "ON CONFLICT (stock_code) DO UPDATE SET " +
                "company_name = EXCLUDED.company_name, " +
                "market_id = EXCLUDED.market_id, " +
                "industry_id = EXCLUDED.industry_id, " +
                "revenue = EXCLUDED.revenue, " +
                "net_income = EXCLUDED.net_income, " +
                "website = EXCLUDED.website"
                ;
    }

    public static class PublicOffering {
        public static final String UPSERT = "INSERT INTO public_offering (" +
                "stock_code, offering_start_date, offering_end_date, confirmed_price, " +
                "desired_price_min, desired_price_max, equal_rate, proportional_rate, " +
                "listing_date, payment_date, refund_date) " +
                "VALUES (" +
                ":stockCode, :offeringStartDate, :offeringEndDate, :confirmedPrice, " +
                ":desiredPriceMin, :desiredPriceMax, :equalRate, :proportionalRate, " +
                ":listingDate, :paymentDate, :refundDate) " +
                "ON CONFLICT (stock_code) DO UPDATE SET " +
                "offering_start_date = EXCLUDED.offering_start_date, " +
                "offering_end_date = EXCLUDED.offering_end_date, " +
                "confirmed_price = EXCLUDED.confirmed_price, " +
                "desired_price_min = EXCLUDED.desired_price_min, " +
                "desired_price_max = EXCLUDED.desired_price_max, " +
                "equal_rate = EXCLUDED.equal_rate, " +
                "proportional_rate = EXCLUDED.proportional_rate, " +
                "listing_date = EXCLUDED.listing_date, " +
                "payment_date = EXCLUDED.payment_date, " +
                "refund_date = EXCLUDED.refund_date"
                ;
    }

    public static class PublicOfferingUnderwriter {
        public static final String UPSERT = "INSERT INTO public_offering_underwriter (" +
                "public_offering_id, underwriter_id)" +
                "VALUES (:publicOfferingId, :underwriterId)" +
                "ON CONFLICT (public_offering_id, underwriter_id)" +
                "DO UPDATE SET " +
                "underwriter_id = EXCLUDED.underwriter_id"
                ;
    }
}
