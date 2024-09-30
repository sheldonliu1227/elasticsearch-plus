package com.sheldon.elasticsearch.configuration;

import com.sheldon.elasticsearch.core.constant.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@ConfigurationProperties(prefix = Constants.ELASTICSEARCH_PLUS)
public class ElasticSearchPlusProperties {
    private static final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private String[] hosts;

    private String user;

    private String password;

    private String configLocation;

    private String[] mapperLocations = new String[]{"classpath*:mapper/**/*_es.xml"};

    public String[] getHosts() {
        return hosts;
    }

    public void setHosts(String[] hosts) {
        this.hosts = hosts;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }

    public String[] getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String[] mapperLocations) {
        this.mapperLocations = mapperLocations;
    }
}