package com.reflection.app;


import com.reflection.annotations.PropertyKey;

public class AppProperties {

    //a bunch of random "properties"
    @PropertyKey("property.name")
    public String name;
    @PropertyKey("property.weight")
    public int weight;
    @PropertyKey("property.logic")
    public boolean isSomething;
    @PropertyKey("property.fruit")
    public Fruits favouriteFruit;
    public char uselessProperty;

    @Override
    public String toString() {
        return "AppProperties{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", isSomething=" + isSomething +
                ", favouriteFruit=" + favouriteFruit +
                ", uselessProperty=" + uselessProperty +
                '}';
    }
}
