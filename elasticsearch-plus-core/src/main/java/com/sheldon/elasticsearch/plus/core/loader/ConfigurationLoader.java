package com.sheldon.elasticsearch.plus.core.loader;

import com.sheldon.elasticsearch.plus.core.ElasticSearchConfiguration;

public interface ConfigurationLoader {
    ElasticSearchConfiguration load();
}
