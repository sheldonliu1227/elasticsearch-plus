package com.sheldon.elasticsearch.core;

import com.sheldon.elasticsearch.core.annotation.NormalField;
import com.sheldon.elasticsearch.core.constant.FieldTypeEnum;

public abstract class AbstractBaseObject {
    // 该字段是内部字段, 用于分页查询时候，采用聚合的方式获取总条数
    @NormalField(fieldType = FieldTypeEnum.BOOLEAN)
    private final Boolean _pageAgg = true;

    protected String rollOverSuffix;

    public String getRollOverSuffix() {
        return rollOverSuffix;
    }

    public void setRollOverSuffix(String rollOverSuffix) {
        this.rollOverSuffix = rollOverSuffix;
    }

    public Boolean getPageAgg() {
        return _pageAgg;
    }
}
