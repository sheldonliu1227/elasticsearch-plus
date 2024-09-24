package com.sheldon.elasticsearch.constant;


import org.elasticsearch.common.geo.GeoPoint;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public enum MappingEnum {
    STRING_2_KEYWORD(String.class, FieldTypeEnum.KEYWORD),
    STRING_2_TEXT(String.class, FieldTypeEnum.TEXT),
    BYTE_2_BYTE(Byte.class, FieldTypeEnum.BYTE),
    SHORT_2_SHORT(Short.class, FieldTypeEnum.SHORT),
    INTEGER_2_INTEGER(Integer.class, FieldTypeEnum.INTEGER),
    LONG_2_LONG(Long.class, FieldTypeEnum.LONG),
    DOUBLE_2_DOUBLE(Double.class, FieldTypeEnum.DOUBLE),
    FLOAT_2_FLOAT(Float.class, FieldTypeEnum.FLOAT),
    BOOLEAN_2_BOOLEAN(Boolean.class, FieldTypeEnum.BOOLEAN),
    DATE_2_DATE(Date.class, FieldTypeEnum.DATE),

    ARRAY_2_KEYWORD(Array.class, FieldTypeEnum.KEYWORD),
    ARRAY_2_BYTE(Array.class, FieldTypeEnum.BYTE),
    ARRAY_2_SHORT(Array.class, FieldTypeEnum.SHORT),
    ARRAY_2_INTEGER(Array.class, FieldTypeEnum.INTEGER),
    ARRAY_2_LONG(Array.class, FieldTypeEnum.LONG),
    ARRAY_2_FLOAT(Array.class, FieldTypeEnum.FLOAT),
    ARRAY_2_DOUBLE(Array.class, FieldTypeEnum.DOUBLE),
    ARRAY_2_BOOLEAN(Array.class, FieldTypeEnum.BOOLEAN),
    ARRAY_2_DATE(Array.class, FieldTypeEnum.DATE),

    INTEGER_2_INTEGER_RANGE(Integer.class, FieldTypeEnum.INTEGER_RANGE),
    LONG_2_LONG_RANGE(Long.class,FieldTypeEnum.LONG_RANGE),
    FLOAT_2_FLOAT_RANGE(Float.class,FieldTypeEnum.FLOAT_RANGE),
    DOUBLE_2_DOUBLE_RANGE(Double.class,FieldTypeEnum.DOUBLE_RANGE),
    DATE_2_DATE_RANGE(Date.class,FieldTypeEnum.DATE_RANGE),
    STRING_2_IP_RANGE(String.class, FieldTypeEnum.IP_RANGE),

    GEO_2_GEO(GeoPoint.class, FieldTypeEnum.GEO_POINT),
    HASH_2_GEO(String.class, FieldTypeEnum.GEO_POINT),
    STRING_2_IP(String.class, FieldTypeEnum.IP),

    MAP_2_OBJECT(Map.class, FieldTypeEnum.OBJECT),
    OBJECT_2_NESTED(Object.class, FieldTypeEnum.NESTED),
    LIST_2_NESTED(List.class, FieldTypeEnum.NESTED),
    ARRAY_2_NESTED(Array.class, FieldTypeEnum.NESTED),
    ;

    private final static Set<? extends Class<?>> ALL_TYPE = Arrays.stream(MappingEnum.values()).map(MappingEnum::getClazz).collect(Collectors.toSet());
    private final Class<?> clazz;
    private final FieldTypeEnum fieldType;

    MappingEnum(Class<?> clazz, FieldTypeEnum fieldType) {
        this.clazz = clazz;
        this.fieldType = fieldType;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public FieldTypeEnum getFieldType() {
        return fieldType;
    }

    public static MappingEnum getMappingEnum(Class<?> javaClass, FieldTypeEnum fieldType) {
        if (Map.class.isAssignableFrom(javaClass)) {
            javaClass = Map.class;
        }
        if (List.class.isAssignableFrom(javaClass)) {
            javaClass = List.class;
        }
        if (Array.class.isAssignableFrom(javaClass)) {
            javaClass = Array.class;
        }
        if (!ALL_TYPE.contains(javaClass)) {
            javaClass = Object.class;
        }
        for (MappingEnum mappingEnum : MappingEnum.values()) {
            if (mappingEnum.getClazz().equals(javaClass) && mappingEnum.getFieldType().equals(fieldType)) {
                return mappingEnum;
            }
        }
        return null;
    }
}
