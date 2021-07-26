package com.reflection.app;

import com.homework.reflection.lib.PropertyInitializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        AppProperties a;
        PropertyInitializer<AppProperties> prop = new PropertyInitializer<>();
        try {
            a = prop.initProperties(AppProperties.class, new File("src/main/resources/file.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(a);
    }
}
