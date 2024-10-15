package com.sheldon.elasticsearch.plus.core.toolkit;

import com.sheldon.elasticsearch.plus.core.annotation.Document;
import com.sheldon.elasticsearch.plus.core.constant.Constants;
import com.sheldon.elasticsearch.plus.core.exception.ClassInaccuracyException;

import java.lang.annotation.Annotation;

public class MetaInfoUtil {

    private MetaInfoUtil() {
    }

    /**
     * 判断是否是目标注解或它的元注解
     *
     * @param annotation       注解
     * @param targetAnnotation 目标注解
     * @return true/false
     */
    public static boolean isTargetOrMetaAnnotation(Annotation annotation, Class<? extends Annotation> targetAnnotation) {
        // 检查注解本身是否是目标注解
        if (annotation.annotationType().equals(targetAnnotation)) {
            return true;
        }

        // 检查注解是否有元注解，并且元注解是目标注解
        for (Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
            if (metaAnnotation.annotationType().equals(targetAnnotation)) {
                return true;
            }
        }
        return false;
    }

    public static void assertDocumentAnnotation(Class<?> clazz) throws ClassInaccuracyException {
        if (!clazz.isAnnotationPresent(Document.class)) {
            throw new ClassInaccuracyException(Constants.CLASS_INACCURACY_DOC_INVALID);
        }
    }
}
