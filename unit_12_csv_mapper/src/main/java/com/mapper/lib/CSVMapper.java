package com.mapper.lib;

import com.mapper.annotations.CSVProperty;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CSVMapper {

    public static <T> List<T> initClass(Class initClass, CSVParser parser) {
        List<T> initClassObjects = new ArrayList<>();
        try {
            for (int i = 0; i < parser.amountOfRows(); i++) {
                initClassObjects.add((T) initClass.getConstructor().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < initClassObjects.size(); i++) {
            String key;
            String value;
            for (Field field : initClass.getFields()) {
                if (field.isAnnotationPresent(CSVProperty.class)) {
                    key = field.getAnnotation(CSVProperty.class).value();
                    value = parser.tableValueByKey(key, i);
                    if (value == null) continue;
                } else
                    continue;
                initField(field, value, initClassObjects.get(i));
            }
        }
        return initClassObjects;
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
