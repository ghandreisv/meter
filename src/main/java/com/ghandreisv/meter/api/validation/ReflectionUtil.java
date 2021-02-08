package com.ghandreisv.meter.api.validation;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

class ReflectionUtil {

    private ReflectionUtil() {
    }

    static String getFieldValue(Object request, String fieldName) {
        try {
            return BeanUtils.getProperty(request, fieldName);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException("Reflection operation failed for field [" + fieldName + "]", e);
        }
    }
}