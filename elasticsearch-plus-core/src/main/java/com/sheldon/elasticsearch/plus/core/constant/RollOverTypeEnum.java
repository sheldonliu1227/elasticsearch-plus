package com.sheldon.elasticsearch.plus.core.constant;


import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 索引滚动类型
 */
public enum RollOverTypeEnum {
    NULL("", null),
    DAY("_d_yyyyMMdd", (date, direction) -> DateUtil.offsetDay(date, direction ? 1 : -1)),
    WEEK("_w_yyyyMMw", (date, direction) -> DateUtil.offsetWeek(date, direction ? 1 : -1)),
    MONTH("_m_yyyyMM", (date, direction) -> DateUtil.offsetMonth(date, direction ? 1 : -1)),
    YEAR("_y_yyyy", (date, direction) -> DateUtil.offsetMonth(date, direction ? 12 : -12)),
    ;

    private static final Pattern DATE_PATTERN = Pattern.compile("_\\(*\\)$");
    private final String format;
    private final BiFunction<Date, Boolean, Date> offset;

    RollOverTypeEnum(String format, BiFunction<Date, Boolean, Date> offset) {
        this.format = format;
        this.offset = offset;
    }

    public String getFormat() {
        return format;
    }

    public BiFunction<Date, Boolean, Date> getOffset() {
        return offset;
    }

    public static String getRollOverSuffix(RollOverTypeEnum rollOverTypeEnum, Date date) {
        if (NULL == rollOverTypeEnum) return rollOverTypeEnum.getFormat();
        Matcher matcher = DATE_PATTERN.matcher(rollOverTypeEnum.getFormat());
        String dateFormat = DateUtil.format(date, matcher.group(1));
        return rollOverTypeEnum.getFormat().replace(matcher.group(1), dateFormat);
    }
}
