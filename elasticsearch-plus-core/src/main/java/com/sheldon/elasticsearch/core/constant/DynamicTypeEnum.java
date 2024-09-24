package com.sheldon.elasticsearch.core.constant;

/**
 * 动态映射类型
 * true： 如果写入文档字段不在mapping范围内，则自动推断mapping，并更新mapping（不推荐使用）
 * false：如果写入文档字段不在mapping范围内，字段仅进行存储，不能使用改字段查询
 * strict：如果写入文档字段不在mapping范围内，则报错
 */
public enum DynamicTypeEnum {
    TRUE("true"),
    FALSE("false"),
    STRICT("strict"),
    ;

    private final String code;

    DynamicTypeEnum(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
