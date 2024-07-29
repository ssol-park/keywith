package com.keywith.api;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public enum ScrapingSections {
    CALENDER_INFO("증시캘린더", Arrays.asList("청약", "상장")),
    COMPANY_INFO("기업개요", Arrays.asList("종목명", "시장구분", "업종", "홈페이지", "매출액", "순이익")),
    STOCK_INFO("공모정보", Arrays.asList("희망공모가액", "확정공모가", "주간사")),
    SUBSCRIPTION_INFO("공모청약일정", Arrays.asList("공모청약일", "납입일", "환불일", "상장일"));

    private final String summary;
    private final List<String> keywords;

    ScrapingSections(String summary, List<String> keywords) {
        this.summary = summary;
        this.keywords = keywords;
    }

    private static final Map<String, ScrapingSections> SECTION_MAP = new HashMap<>();

    static {
        for (ScrapingSections value : values()) {
            SECTION_MAP.put(value.getSummary(), value);
        }
    }

    public static List<String> getKeywordsBySummary (String summary) {
        return SECTION_MAP.get(summary).getKeywords();
    }

    public static String getSummary(ScrapingSections section) {
        return section.getSummary();
    }
}