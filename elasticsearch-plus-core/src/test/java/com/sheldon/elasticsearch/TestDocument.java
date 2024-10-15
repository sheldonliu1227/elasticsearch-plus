package com.sheldon.elasticsearch;

import com.sheldon.elasticsearch.plus.core.AbstractBaseObject;
import com.sheldon.elasticsearch.plus.core.annotation.Document;
import com.sheldon.elasticsearch.plus.core.annotation.DocumentAlias;
import com.sheldon.elasticsearch.plus.core.annotation.field.*;
import com.sheldon.elasticsearch.plus.core.annotation.field.numeric.MappingInteger;
import com.sheldon.elasticsearch.plus.core.annotation.field.numeric.MappingShort;
import com.sheldon.elasticsearch.plus.core.annotation.field.parameter.IndexPrefixes;
import com.sheldon.elasticsearch.plus.core.constant.RollOverTypeEnum;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(
        indexName = "test_index",
        alias = @DocumentAlias(indexAlias = "test_alias", rollOverType = RollOverTypeEnum.DAY)
)
public class TestDocument extends AbstractBaseObject {
    @MappingKeyWord
    private String name;

    @MappingText(analyzer = "whitespace", index = false, index_prefixes = @IndexPrefixes(minChars = 3))
    private String remark;

    @MappingObject
    private Demo demo;

    @MappingNested
    private List<Demo> demoList;

    @MappingNested
    private Demo[] demoArray;

    @MappingObject(child = CustomParser.class)
    private Map<String, Object> testMap;


    public static class Demo {
        @MappingShort
        private Short aShort;

        @MappingInteger
        private Integer anInt;

        @MappingDate(format = "yyyy-MM-dd HH:mm:ss")
        private Date date;
    }
}
