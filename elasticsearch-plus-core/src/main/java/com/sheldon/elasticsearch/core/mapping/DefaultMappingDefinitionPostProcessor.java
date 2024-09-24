package com.sheldon.elasticsearch.core.mapping;

import com.sheldon.elasticsearch.core.exception.ClassInaccuracyException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.sheldon.elasticsearch.core.XContentConverter.getFieldMappingDefinition;

public class DefaultMappingDefinitionPostProcessor implements MappingDefinitionPostProcessor {
    @Override
    public List<MappingDefinition> process(Field field) throws ClassInaccuracyException {
        List<MappingDefinition> mappingDefinitions = new ArrayList<>();
        for (Field declaredField : field.getType().getDeclaredFields()) {
            try {
                MappingDefinition fieldMappingDefinition = getFieldMappingDefinition(declaredField);
                mappingDefinitions.add(fieldMappingDefinition);
            } catch (Exception e) {
                throw new ClassInaccuracyException(e);
            }
        }
        return mappingDefinitions;
    }
}
