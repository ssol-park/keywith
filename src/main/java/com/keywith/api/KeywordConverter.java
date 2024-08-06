package com.keywith.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class KeywordConverter {
    public static final String DELIMITERS_REGEX = "[,\\.]";
    public static final String RANGE_DELIMITER = "~";
    private static final Map<String, String> KEYWORD_MAP = new HashMap<>();

    static {
        // 한글 키워드를 영문 키워드로 매핑
        KEYWORD_MAP.put("종목코드", "stockCode");
        KEYWORD_MAP.put("종목명", "stockName");
        KEYWORD_MAP.put("시장구분", "marketType");
        KEYWORD_MAP.put("업종", "industry");
        KEYWORD_MAP.put("홈페이지", "website");
        KEYWORD_MAP.put("매출액", "revenue");
        KEYWORD_MAP.put("순이익", "netIncome");
        KEYWORD_MAP.put("청약경쟁률", "subscriptionCompetitionRate");
        KEYWORD_MAP.put("확정공모가", "confirmedOfferingPrice");
        KEYWORD_MAP.put("희망공모가액", "desiredOfferingPrice");
        KEYWORD_MAP.put("주간사", "underwriters");
        KEYWORD_MAP.put("공모청약일", "offeringSchedule");
        KEYWORD_MAP.put("납입일", "paymentDate");
        KEYWORD_MAP.put("환불일", "refundDate");
        KEYWORD_MAP.put("상장일", "listingDate");
    }

    public static Map<String, Object> convert(String koreanKeyword, String value) {
        Map<String, Object> result = new HashMap<>();

        switch (koreanKeyword) {
            case "희망공모가액":
                String[] priceRange = StringUtil.removeKeywordAndSplit(value, "원", RANGE_DELIMITER);
                result.put("desiredOfferingPriceMin", StringUtil.parseToLong(priceRange[0]));
                result.put("desiredOfferingPriceMax", StringUtil.parseToLong(priceRange[1]));
                break;

            case "확정공모가":
                result.put(KEYWORD_MAP.get(koreanKeyword), StringUtil.parseToLong(StringUtil.removeKeywords(value, "원", ",")));
                break;

            case "주간사":
                String[] underwriters = StringUtil.splitByDelimiter(value, DELIMITERS_REGEX);

                result.put(KEYWORD_MAP.get(koreanKeyword), Arrays.asList(underwriters));
                break;

            case "매출액":
            case "순이익":
                long amount = StringUtil.parseMillionUnit(value);
                result.put(KEYWORD_MAP.get(koreanKeyword), amount);
                break;

            case "공모청약일":
                String[] scheduleRange = StringUtil.splitByDelimiter(value, RANGE_DELIMITER);
                result.put("offeringScheduleStart", scheduleRange[0]);
                result.put("offeringScheduleEnd", scheduleRange[1]);
                break;

            default:
                result.put(KEYWORD_MAP.getOrDefault(koreanKeyword, koreanKeyword), value);
                break;
        }

        return result;
    }
}
