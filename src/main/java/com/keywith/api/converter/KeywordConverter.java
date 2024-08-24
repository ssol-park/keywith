package com.keywith.api.converter;

import com.keywith.api.utils.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
        KEYWORD_MAP.put("청약경쟁률", "competitionRate");
        KEYWORD_MAP.put("확정공모가", "confirmedPrice");
        KEYWORD_MAP.put("주간사", "underwriters");
        KEYWORD_MAP.put("납입일", "paymentDate");
        KEYWORD_MAP.put("환불일", "refundDate");
        KEYWORD_MAP.put("상장일", "listingDate");
    }

    public static Map<String, Object> convert(String koreanKeyword, String value) {
        Map<String, Object> result = new HashMap<>();

        switch (koreanKeyword) {
            case "희망공모가액":
                String[] priceRange = StringUtil.removeKeywordAndSplit(value, "원", RANGE_DELIMITER);
                result.put("desiredPriceMin", StringUtil.parseToLong(priceRange[0]));
                result.put("desiredPriceMax", StringUtil.parseToLong(priceRange[1]));
                break;

            case "확정공모가":
                result.put(KEYWORD_MAP.get(koreanKeyword), StringUtil.parseToLong(StringUtil.removeKeywords(value, "원", ",")));
                break;

            case "순이익", "매출액":
                long amount = StringUtil.parseMillionUnit(value);
                result.put(KEYWORD_MAP.get(koreanKeyword), amount);
                break;

            case "공모청약일":
                String[] scheduleRange = StringUtil.splitByDelimiter(value, RANGE_DELIMITER);
                result.put("offeringStartDate", scheduleRange[0]);
                result.put("offeringEndDate", scheduleRange[1]);
                break;

            case "청약경쟁률":
                String cleanedValue = StringUtil.removeKeywords(value, "(", ")");
                String[] competitionRate = StringUtil.splitByDelimiter(cleanedValue, "비례");

                result.put("equalRate", competitionRate[0]);
                result.put("proportionalRate", competitionRate[1]);
                break;

            default:
                result.put(KEYWORD_MAP.getOrDefault(koreanKeyword, koreanKeyword), value);
                break;
        }

        return result;
    }
}
