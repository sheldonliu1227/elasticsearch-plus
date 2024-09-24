package com.sheldon.elasticsearch;

import com.sheldon.elasticsearch.core.AbstractBaseObject;
import com.sheldon.elasticsearch.core.constant.FieldTypeEnum;
import com.sheldon.elasticsearch.core.constant.RollOverTypeEnum;
import com.sheldon.elasticsearch.core.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(
        indexName = "test_index",
        alias = @Alias(indexAlias = "test_alias", rollOverType = RollOverTypeEnum.DAY)
)
public class TestDocument extends AbstractBaseObject {
    @NormalField(fieldType = FieldTypeEnum.KEYWORD)
    private String name;

    @NormalField(fieldType = FieldTypeEnum.TEXT)
    private String remark;

    @ObjectMappingDefinitionField
    private Demo demo;

    @NestedMappingDefinitionField(nestedClass = Demo.class)
    private List<Demo> demoList;

    @NestedMappingDefinitionField(nestedClass = Demo.class)
    private Demo[] demoArray;

    @ObjectMappingDefinitionField(processor = MapProcessor.class)
    private Map<String, Object> testMap;


    public static class Demo {
        @NormalField(fieldType = FieldTypeEnum.SHORT)
        private Short aShort;

        @NormalField(fieldType = FieldTypeEnum.INTEGER)
        private Integer anInt;

        @NormalField(fieldType = FieldTypeEnum.LONG_RANGE)
        private Long aLong;

        @NormalField(fieldType = FieldTypeEnum.FLOAT_RANGE)
        private Float aFloat;

        @NormalField(fieldType = FieldTypeEnum.DOUBLE_RANGE)
        private Double aDouble;

        @NormalField(fieldType = FieldTypeEnum.BOOLEAN)
        private Boolean aBoolean;

        @NormalField(fieldType = FieldTypeEnum.DATE_RANGE, fieldFormat = "yyyy-MM-dd HH:mm:ss")
        private Date date;
    }
}
