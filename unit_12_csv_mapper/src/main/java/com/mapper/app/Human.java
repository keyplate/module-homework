package com.mapper.app;


import com.mapper.annotations.CSVProperty;

public class Human {

    //a bunch of random "properties"
    @CSVProperty("first")
    public String name;
    @CSVProperty("last")
    public String lastName;
    @CSVProperty("age")
    public int age;
    @CSVProperty("state")
    public String state;

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", state='" + state + '\'' +
                ", uselessProperty=" +
                '}';
    }
}
