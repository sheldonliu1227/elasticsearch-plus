package com.sheldon.elasticsearch.core;

import com.sheldon.elasticsearch.core.query.Page;
import com.sheldon.elasticsearch.core.query.QueryWrapper;

import java.util.List;
import java.util.concurrent.Future;

public interface BaseMapper<T extends AbstractBaseObject> {
    /**
     * 初始化索引，索引存在则更新, 如果索引不存在则创建
     *
     * @param doc 对象实例rollOverSuffix，在alias为false时候有效，初始化索引<Document注解中的indexName + 实例中的rollOverSuffix>
     * @param alias 如果为true，并且Document注解配置了Alias注解，则对别名指向对所有滚动索引进行初始化
     * @return 成功数量
     */
    Future<Integer> initialize(T doc, Boolean alias);

    /**
     * 插入或者更新一条或者多条数据
     *
     * @param doc 如果实例rollOverSuffix有值，则插入到指定对滚动索引中，否则插入到当前滚动索引中
     * @return 成功数量
     */
    Future<Integer> insert(T... doc);

    /**
     * 仅更新一条或者多条数据，如果数据不存在，则丢弃
     *
     * @param doc 如果实例rollOverSuffix有值，则更新到指定对滚动索引中，否则更新到当前滚动索引中
     * @return 成功数量
     */
    Future<Integer> update(T... doc);

    /**
     * 删除一条或者多条数据，如果数据不存在，则丢弃
     *
     * @param doc 如果实例rollOverSuffix有值，则删除到指定对滚动索引中，否则更新到当前滚动索引中
     * @return 成功数量
     */
    Future<Integer> delete(T... doc);

    /**
     * 查询一条或者多条数据
     *
     * @param qw 查询条件包装器，使用build方法产出对应对查询条件
     * @return 查询结果
     */
    <QW extends QueryWrapper<T>> Future<List<T>> query(QW qw);

    /**
     * 查询分页数据
     *
     * @param qw 查询条件包装器，使用build方法产出对应对查询条件
     * @param trackTotalHits 是否追踪总数,如果为true，则使用trace_total_hits返回总数，否则使用terms聚合agg字段获取总数
     * @return 查询结果
     */
    <QW extends QueryWrapper<T>> Future<Page<T>> queryPage(QW qw, boolean trackTotalHits);
}
