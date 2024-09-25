package com.sheldon.elasticsearch.core.query;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;

import java.util.List;

public class Page<T> {
    private int pageNum;
    private int pageSize;
    private int total;
    private int pages;
    private boolean hasNext;
    private List<T> list;
    private List<Aggregation> aggregations;
}
