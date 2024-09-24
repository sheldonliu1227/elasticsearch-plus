package com.sheldon.elasticsearch;

import com.sheldon.elasticsearch.constant.FieldTypeEnum;
import com.sheldon.elasticsearch.constant.RollOverTypeEnum;
import com.sheldon.elasticsearch.core.annotation.*;

import java.util.Date;
import java.util.Map;

@Document(
        indexName = "test_index",
        alias = @Alias(indexAlias = "test_alias", rollOverType = RollOverTypeEnum.DAY)
)
public class TestDocument extends AbstractBaseObject {

    @ClassMappingDefinitionField(nestedClass = Demo.class)
    private Demo demo;

    @DynamicMappingDefinitionField(processor = MapProcessor.class)
    private Map<String, Object> testMap;



    public static class Demo {
        @NormalField(fieldType = FieldTypeEnum.BYTE)
        private Byte aByte;

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
