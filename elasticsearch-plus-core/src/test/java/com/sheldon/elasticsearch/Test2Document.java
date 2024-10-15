package com.sheldon.elasticsearch;

import com.sheldon.elasticsearch.plus.core.annotation.Document;
import com.sheldon.elasticsearch.plus.core.annotation.DocumentAlias;
import com.sheldon.elasticsearch.plus.core.annotation.field.MappingKeyWord;
import com.sheldon.elasticsearch.plus.core.annotation.field.MappingText;
import com.sheldon.elasticsearch.plus.core.constant.RollOverTypeEnum;

@Document(
        indexName = "test2_index",
        alias = @DocumentAlias(indexAlias = "test2_alias", rollOverType = RollOverTypeEnum.DAY)
)
public class Test2Document extends TestDocument {

    @MappingText(analyzer = "whitespace")
    private String name;

    @MappingKeyWord(index = false)
    private String remark;

}
