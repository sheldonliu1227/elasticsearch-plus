package com.sheldon.elasticsearch.plus.spring.annatation;

import java.lang.annotation.*;

/**
 * 标记一个类是Spring的bean，用以显示注册bean，如果类继承类BaseMapper，无需该注解也能自动注册bean
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface ElasticSearchMapper {
}
