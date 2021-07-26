package com.reflection.lib;

import com.homework.reflection.annotations.PropertyKey;
import com.homework.reflection.app.AppProperties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PropertyInitializer<T> {
    private Properties properties;
    private Class initClass;
    private T initClassObject;

    public T initProperties(Class<T> initClass, File propertyFile) throws IOException {
        this.initClass = initClass;
        this.properties = new Properties();
        try(var fileReader = new FileReader(propertyFile)){
            properties.load(fileReader);
        }
        try {
            initClassObject = initClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        initClass();
        return initClassObject;
    }

    private void initClass() {
        String propValue;
        for (Field field : initClass.getFields()) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                propValue = properties.getProperty(field.getAnnotation(PropertyKey.class).value());
                if (propValue == null) continue;
            } else
                continue;
            initField(field, propValue);
        }
    }

    private void initField(Field field, String propertyValue) {
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
