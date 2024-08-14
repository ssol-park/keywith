package com.keywith.api.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "scrap")
public class ScrapProperties {
    private String domain;
    private String baseUrl;
}
