package com.sheldon.elasticsearch.configuration;

import com.sheldon.elasticsearch.core.constant.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@ConfigurationProperties(prefix = Constants.ELASTICSEARCH_PLUS)
public class ElasticSearchPlusProperties {
    private static final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private String configLocation;

    private String[] mapperLocations = new String[]{"classpath*:mapper/**/*_es.xml"};


}