package com.sheldon.elasticsearch.core;

import com.sheldon.elasticsearch.core.annotation.NormalField;

public abstract class AbstractBaseObject {
    @NormalField
    private final String agg = "agg";

    protected String rollOverSuffix;

    public String getRollOverSuffix() {
        return rollOverSuffix;
    }

    public void setRollOverSuffix(String rollOverSuffix) {
        this.rollOverSuffix = rollOverSuffix;
    }

    public String getAgg() {
        return agg;
    }
}
