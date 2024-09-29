package com.sheldon.elasticsearch.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ElasticSearchPlusProperties.class)
public class ElasticSearchPlusConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ElasticSearchPlusConfiguration.class);

    private final ElasticSearchPlusProperties properties;

    public ElasticSearchPlusConfiguration(ElasticSearchPlusProperties properties) {
        this.properties = properties;
    }
}
