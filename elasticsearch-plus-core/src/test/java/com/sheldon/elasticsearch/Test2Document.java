package com.sheldon.elasticsearch;

import com.sheldon.elasticsearch.core.annotation.*;
import com.sheldon.elasticsearch.core.constant.FieldTypeEnum;
import com.sheldon.elasticsearch.core.constant.RollOverTypeEnum;

@Document(
        indexName = "test2_index",
        alias = @Alias(indexAlias = "test2_alias", rollOverType = RollOverTypeEnum.DAY)
)
public class Test2Document extends TestDocument {
    @NormalField(fieldType = FieldTypeEnum.TEXT)
    private String name;

    @NormalField(fieldType = FieldTypeEnum.KEYWORD)
    private String remark;

}
