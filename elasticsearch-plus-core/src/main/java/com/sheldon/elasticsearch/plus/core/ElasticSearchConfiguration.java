package com.sheldon.elasticsearch.core;

import com.sheldon.elasticsearch.core.binding.MapperRegistry;

public class ElasticSearchConfiguration {
    private String[] hosts;
    private String user;
    private String password;
    private MapperRegistry mapperRegistry;
}
