package com.sheldon.elasticsearch.core.constant;

/**
 * elasticsearch field type
 */
public enum FieldTypeEnum {
    // >>>>>>>> 核心类型 <<<<<<<<<<
    TEXT("text"),
    KEYWORD("keyword"),

    SHORT("short"),
    INTEGER("integer"),
    LONG("long"),
    DOUBLE("double"),
    FLOAT("float"),

    BOOLEAN("boolean"),
    DATE("date"),

    // >>>>>>>> 复杂类型 <<<<<<<<<<
    OBJECT("object"),
    NESTED("nested"),

    // >>>>>>>> 特殊数据数据类型 <<<<<<<<<<
    // 二进制、IP、地理坐标点、地理空间形状、自动补全、词数、反向过滤
    BINARY("binary"), // 用于存储二进制数据。数据在 Elasticsearch 中是 Base64 编码的。
    IP("ip"), // 用于存储 IP 地址，支持 IPv4 和 IPv6
    GEO_POINT("geo_point"), // 用于存储地理坐标点（如经纬度）。支持地理位置相关查询，如距离查询、地理围栏查询等。
    GEO_SHAPE("geo_shape"), // 用于存储更复杂的地理空间形状，如多边形、线、圆等。支持地理空间形状查询。
    COMPLETION("completion"), // 用于实现自动补全功能的字段类型，常用于搜索建议。
    TOKEN_COUNT("token_count"), // 用于存储分词后的词数，常用于统计文档中的词数。
    PERCOLATOR("percolator"), // 用于实现反向过滤查询的字段类型，常用于实时搜索。

    // >>>>>>>> 范围类型 <<<<<<<<<<
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
