package com.reflection.app;

import com.reflection.lib.PropertyInitializer;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        AppProperties a;
        try(FileReader fl = new FileReader(args[0])) {
            Properties prop = new Properties();
            prop.load(fl);
            a = PropertyInitializer.initClass(AppProperties.class, prop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(a);
    }
}
