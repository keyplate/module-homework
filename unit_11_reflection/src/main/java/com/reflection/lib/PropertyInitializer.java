package com.reflection.lib;

import com.reflection.annotations.PropertyKey;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PropertyInitializer {

    public static <T> T initClass(Class initClass, Properties properties) {
        Object initClassObject;
        try {
            initClassObject = initClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        String propValue;
        for (Field field : initClass.getFields()) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                propValue = properties.getProperty(field.getAnnotation(PropertyKey.class).value());
                if (propValue == null) continue;
            } else
                continue;
            initField(field, propValue, initClassObject);
        }
        return (T)initClassObject;
    }

    private static void initField(Field field, String propertyValue, Object initClassObject) {
        try {
            Class<?> fieldType = field.getType();
            if (fieldType == String.class) {
                field.set(initClassObject, propertyValue);
            } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                field.setBoolean(initClassObject, Boolean.parseBoolean(propertyValue));
            } else if (fieldType == Integer.class || fieldType == int.class) {
                field.setInt(initClassObject, Integer.parseInt(propertyValue));
            } else if (fieldType == Long.class || fieldType == long.class) {
                field.set(initClassObject, Long.parseLong(propertyValue));
            } else if (fieldType == Float.class || fieldType == float.class) {
                field.set(initClassObject, Float.parseFloat(propertyValue));
            } else if (fieldType == Double.class || fieldType == double.class) {
                field.set(initClassObject, Double.parseDouble(propertyValue));
            } else if (fieldType.isEnum()) {
                field.set(initClassObject, Enum.valueOf((Class<Enum>) fieldType, propertyValue));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
