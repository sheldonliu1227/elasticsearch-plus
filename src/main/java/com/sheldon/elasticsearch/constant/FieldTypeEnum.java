package com.sheldon.elasticsearch.constant;

public enum FieldTypeEnum {
    TEXT("text"),
    KEYWORD("keyword"),

    BYTE("byte"),
    SHORT("short"),
    INTEGER("integer"),
    LONG("long"),
    DOUBLE("double"),
    FLOAT("float"),
    BOOLEAN("boolean"),
    DATE("date"),

    BINARY("binary"),
    IP("ip"),
    GEO_POINT("geo_point"),

    OBJECT("object"),
    NESTED("nested"),
    ARRAY("array"),

    INTEGER_RANGE("integer_range"),
    LONG_RANGE("long_range"),
    FLOAT_RANGE("float_range"),
    DOUBLE_RANGE("double_range"),
    DATE_RANGE("date_range"),
    IP_RANGE("ip_range"),
    ;

    private final String type;

    FieldTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
