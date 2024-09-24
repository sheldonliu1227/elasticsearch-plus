package com.sheldon.elasticsearch.core.mapping;

import java.util.List;

public interface MappingDefinitionPostProcessor {
    List<MappingDefinition> process();
}
