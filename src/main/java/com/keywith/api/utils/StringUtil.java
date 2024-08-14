package com.keywith.api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    public static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");
    public static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

    public static String removeKeyword(String value, String keyword) {
        value = removeWhitespace(value);

        if (StringUtils.hasText(value) && StringUtils.hasText(keyword)) {
            return value.replace(keyword, "");
        }
        return value;
    }

    public static String removeKeywords(String value, String... keywords) {
        for (String keyword : keywords) {
            value = removeKeyword(value, keyword);
        }

        return value;
    }

    public static String removeWhitespace(String value) {
        if(StringUtils.hasText(value)) {
            return WHITESPACE_PATTERN.matcher(value).replaceAll("");
        }

        return "";
    }

    public static long parseToLong(String value) {
        if (value != null && NUMBER_PATTERN.matcher(value).matches()) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                log.error("NumberFormatException: Unable to parse value to long", e);
            }
        }

        return 0;
    }
    public static long parseMillionUnit(String value) {
        Matcher matcher = NUMBER_PATTERN.matcher(value);
        StringBuilder numberPart = new StringBuilder();
        while (matcher.find()) {
            numberPart.append(matcher.group());
        }

        String numberString = numberPart.toString();

        return StringUtils.hasText(numberString) ? Long.parseLong(numberString) * 1_000_000L : 0;
    }

    public static String[] splitByDelimiter(String value, String delimiter) {
        if (StringUtils.hasText(value) && StringUtils.hasText(delimiter)) {
            return removeWhitespace(value).split(Pattern.quote(delimiter));
        }
        return new String[0];
    }

    public static String[] removeKeywordAndSplit(String value, String keyword, String delimitersRegex) {
        return splitByDelimiter(removeKeyword(value, keyword), delimitersRegex);
    }
}
