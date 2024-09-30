package com.sheldon.elasticsearch.core.loader;

import com.sheldon.elasticsearch.core.ElasticSearchConfiguration;

public interface ConfigurationLoader {
    ElasticSearchConfiguration load();
}
