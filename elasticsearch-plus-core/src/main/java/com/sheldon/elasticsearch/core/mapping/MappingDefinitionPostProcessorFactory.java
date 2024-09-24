package com.sheldon.elasticsearch.core.mapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MappingDefinitionPostProcessorFactory {
    private final static Map<Class<? extends MappingDefinitionPostProcessor>, MappingDefinitionPostProcessor> INSTANCES = new ConcurrentHashMap<>();

    public static <T extends MappingDefinitionPostProcessor> MappingDefinitionPostProcessor getMappingDefinitionPostProcessor(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        MappingDefinitionPostProcessor mappingDefinitionPostProcessor = INSTANCES.get(clazz);
        if (null != mappingDefinitionPostProcessor) {
            return mappingDefinitionPostProcessor;
        }
        mappingDefinitionPostProcessor = clazz.newInstance();
        INSTANCES.put(clazz, mappingDefinitionPostProcessor);
        return mappingDefinitionPostProcessor;
    }
}
