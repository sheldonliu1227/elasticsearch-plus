package com.sheldon.elasticsearch.core.constant;


import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 索引滚动类型
 */
public enum RollOverTypeEnum {
    NULL("", null, null),
    DAY("_d_yyyyMMdd", d -> DateUtil.offsetDay(d, 1), d -> DateUtil.offsetDay(d, -1)),
    WEEK("_w_yyyyMMw", d -> DateUtil.offsetWeek(d, 1), d -> DateUtil.offsetWeek(d, -1)),
    MONTH("_m_yyyyMM", d -> DateUtil.offsetMonth(d, 1), d -> DateUtil.offsetMonth(d, -1)),
    YEAR("_y_yyyy", d -> DateUtil.offsetMonth(d, 12), d -> DateUtil.offsetMonth(d, -12)),
    ;

    private static final Pattern DATE_PATTERN = Pattern.compile("_\\(*\\)$");
    private final String format;
    private final Function<Date, Date> next;
    private final Function<Date, Date> prev;

    RollOverTypeEnum(String format, Function<Date, Date> next, Function<Date, Date> prev) {
        this.format = format;
        this.next = next;
        this.prev = prev;
    }

    public String getFormat() {
        return format;
    }

    public Function<Date, Date> getNext() {
        return next;
    }

    public Function<Date, Date> getPrev() {
        return prev;
    }

    public static String getRollOverSuffix(RollOverTypeEnum rollOverTypeEnum, Date date) {
        if (NULL == rollOverTypeEnum) return rollOverTypeEnum.getFormat();
        Matcher matcher = DATE_PATTERN.matcher(rollOverTypeEnum.getFormat());
        String dateFormat = DateUtil.format(date, matcher.group(1));
        return rollOverTypeEnum.getFormat().replace(matcher.group(1), dateFormat);
    }
}
