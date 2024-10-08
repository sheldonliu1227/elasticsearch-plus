package com.sheldon.elasticsearch.plus.core.toolkit;

import com.sheldon.elasticsearch.plus.core.AbstractBaseObject;
import com.sheldon.elasticsearch.plus.core.annotation.Document;
import com.sheldon.elasticsearch.plus.core.annotation.NormalField;
import com.sheldon.elasticsearch.plus.core.constant.Constants;
import com.sheldon.elasticsearch.plus.core.exception.ClassInaccuracyException;
import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClassValidatorUtils {

    private ClassValidatorUtils() {}

    // 判断是否是目标注解或它的元注解
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

    public static boolean isNormalField(@NotNull Annotation annotation) {
        return isTargetOrMetaAnnotation(annotation, NormalField.class);
    }

    public static boolean isBaseObject(@NotNull Class<?> clazz) {
        return AbstractBaseObject.class.isAssignableFrom(clazz);
    }

    public static void assertDocumentAnnotation(@NotNull Class<?> clazz) throws ClassInaccuracyException {
        if (!clazz.isAnnotationPresent(Document.class)) {
            throw new ClassInaccuracyException(Constants.CLASS_INACCURACY_DOC_INVALID);
        }
    }

    public static boolean isNormalFieldOrChild(@NotNull Annotation annotation) {
        // 检查注解本身是否是目标注解
        if (annotation.annotationType().equals(NormalField.class)) {
            return true;
        }

        // 检查注解是否有元注解，并且元注解是目标注解
        for (Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
            if (metaAnnotation.annotationType().equals(NormalField.class)) {
                return true;
            }
        }
        return false;
    }

    public static NormalField getNormalField(@NotNull Annotation annotation) {
        // 检查注解本身是否是目标注解
        if (annotation.annotationType().equals(NormalField.class)) {
            return (NormalField)annotation;
        }

        // 检查注解是否有元注解，并且元注解是目标注解
        for (Annotation metaAnnotation : annotation.annotationType().getAnnotations()) {
            if (metaAnnotation.annotationType().equals(NormalField.class)) {
                return (NormalField)metaAnnotation;
            }
        }
        return null;
    }

    public static void assertFieldTypeWithRightEnum(@NotNull Field field) throws ClassInaccuracyException {
        Class<?> type = field.getType();

        List<NormalField> normalFields = Arrays.stream(field.getAnnotations()).map(ClassValidatorUtils::getNormalField)
                .filter(Objects::nonNull).collect(Collectors.toList());
        if (normalFields.isEmpty()) return;
        if (normalFields.size() > 1) {
            throw new ClassInaccuracyException(Constants.CLASS_INACCURACY_FIELD_ANNOTATION_INVALID);
        }
        NormalField normalField = normalFields.get(0);
        Arrays.stream(normalField.fieldType().getClasses()).anyMatch(clazz -> clazz.isAssignableFrom(type));
    }

    public static void assertClassWithRightAnnotation(@NotNull Class<?> clazz) throws ClassInaccuracyException {
        if (!isBaseObject(clazz)) {
            throw new ClassInaccuracyException(Constants.CLASS_INACCURACY_DOC_INVALID);
        }
        for (Field field : clazz.getDeclaredFields()) {
            assertFieldTypeWithRightEnum(field);
        }
    }

}
