package com.sheldon.elasticsearch.core.constant;

public interface ConstantsException {
    String CLASS_INACCURACY_DOC_INVALID = "the doc must be not null and must be annotated by @Document";
    String CLASS_INACCURACY_DATE_MUST_WITH_FORMAT = "the format of date field must be not null and not empty";
    String CLASS_INACCURACY_FIELD_MAPPING_NOT_SUPPORTED = "the field type convert mapping is not supported";
    String CLASS_INACCURACY_FIELD_ANNOTATION_INVALID = "the field annotation is invalid, must with @NormalField or its meta-annotation";
    String CLASS_INACCURACY_NESTED_DATA_MUST_WITH_CHILDREN =  "the nested data or object data is must with children fields";

    String ILLEGAL_ARGUMENT_EXCEPTION_INITIALIZE = "initialize method with illegal argument";
}
