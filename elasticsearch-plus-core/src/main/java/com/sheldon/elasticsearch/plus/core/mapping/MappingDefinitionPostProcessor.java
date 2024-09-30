package com.sheldon.elasticsearch.core.mapping;

import com.sheldon.elasticsearch.core.exception.ClassInaccuracyException;

import java.lang.reflect.Field;
import java.util.List;

public interface MappingDefinitionPostProcessor {
    List<MappingDefinition> process(Field field) throws ClassInaccuracyException;
}
