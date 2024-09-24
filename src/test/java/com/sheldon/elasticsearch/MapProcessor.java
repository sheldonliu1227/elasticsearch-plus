package com.sheldon.elasticsearch;

import com.sheldon.elasticsearch.constant.MappingEnum;
import com.sheldon.elasticsearch.core.mapping.MappingDefinition;
import com.sheldon.elasticsearch.core.mapping.MappingDefinitionPostProcessor;

import java.util.*;


public class MapProcessor implements MappingDefinitionPostProcessor {

    @Override
    public List<MappingDefinition> process() {
        List<MappingDefinition> list = new ArrayList<>();
        list.add(new MappingDefinition("keyword", MappingEnum.STRING_2_KEYWORD, null));
        list.add(new MappingDefinition("text", MappingEnum.STRING_2_TEXT, null));
        list.add(new MappingDefinition("date", MappingEnum.DATE_2_DATE_RANGE, null));
        list.add(new MappingDefinition("integer_range", MappingEnum.INTEGER_2_INTEGER_RANGE, null));
        list.add(new MappingDefinition("long_range", MappingEnum.LONG_2_LONG_RANGE, null));
        list.add(new MappingDefinition("date_range", MappingEnum.DATE_2_DATE_RANGE, "yyyy-MM-dd HH:mm:ss"));

        return list;
    }
}
