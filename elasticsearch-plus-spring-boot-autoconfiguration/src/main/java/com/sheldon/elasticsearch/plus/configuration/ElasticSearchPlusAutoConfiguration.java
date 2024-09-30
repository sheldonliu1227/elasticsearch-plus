package com.sheldon.elasticsearch.configuration;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(ElasticSearchPlusProperties.class)
public class ElasticSearchPlusAutoConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ElasticSearchPlusAutoConfiguration.class);

    private final ElasticSearchPlusProperties properties;

    public ElasticSearchPlusAutoConfiguration(ElasticSearchPlusProperties properties) {
        this.properties = properties;
    }


    @Bean
    @ConditionalOnMissingBean
    public RestClient restClient() {
        List<HttpHost> hosts = new ArrayList<>();
        for (String host : properties.getHosts()) {
            String[] split = host.split(":");
            hosts.add(new HttpHost(split[0], Integer.parseInt(split[1])));
        }
        return RestClient.builder(hosts.toArray(new HttpHost[0])).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public RestClientTransport restClientTransport(RestClient restClient) {
        return new RestClientTransport(restClient, new JacksonJsonpMapper());
    }

    @Bean
    @ConditionalOnMissingBean
    public ElasticsearchAsyncClient elasticsearchAsyncClient(RestClientTransport restClientTransport) {
        return new ElasticsearchAsyncClient(restClientTransport);
    }
}
