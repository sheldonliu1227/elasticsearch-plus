package com.sheldon.elasticsearch.core;

import com.sheldon.elasticsearch.core.query.QueryWrapper;

import java.util.List;
import java.util.concurrent.Future;

public interface BaseMapper<T extends AbstractBaseObject> {
    /**
     * 初始化索引, 如果索引不存在则创建
     *
     * @param doc 对象实例，如果
     * @param alias
     * @return
     */
    Future<Integer> initialize(T doc, Boolean alias);

    Future<Integer> insert(T... doc);

    Future<Integer> update(T... doc);

    Future<Integer> delete(T... doc);

    <QW extends QueryWrapper<T>> Future<List<T>> query(QW qw);
}
