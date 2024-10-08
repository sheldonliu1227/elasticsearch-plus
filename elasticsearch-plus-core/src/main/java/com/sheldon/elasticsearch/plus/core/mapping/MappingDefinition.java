package com.sheldon.elasticsearch.core.mapping;

import com.sheldon.elasticsearch.core.XContentConverter;
import com.sheldon.elasticsearch.core.constant.MappingEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MappingDefinition {
    private final String key;
    private final MappingEnum mapping;
    private final String format;
    private final List<MappingDefinition> children = new ArrayList<>();

    public MappingDefinition(String key, MappingEnum mapping, String format) {
        this.key = key;
        this.mapping = mapping;
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public MappingEnum getMapping() {
        return mapping;
    }

    public String getKey() {
        return key;
    }

    public List<MappingDefinition> getChildren() {
        return children;
    }

    public void addChild(MappingDefinition child) {
        XContentConverter.addMappingDefinition(children, child);
    }

    public void addAll(Collection<MappingDefinition> cr) {
        for (MappingDefinition mappingDefinition : cr) {
            addChild(mappingDefinition);
        }
    }

    public void clearChildren() {
        children.clear();
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public boolean isNestedMapping() {
        return MappingEnum.MAP_2_OBJECT.equals(mapping) ||
                MappingEnum.OBJECT_2_OBJECT.equals(mapping) ||
                MappingEnum.LIST_2_NESTED.equals(mapping) ||
                MappingEnum.ARRAY_2_NESTED.equals(mapping);
    }

    public boolean isNotReady() {
        return isNestedMapping() && !hasChildren();
    }
}
