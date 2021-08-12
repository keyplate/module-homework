package com.multithreading.hello;

public class Printer {

    public static synchronized void printHello(Thread thread) {
        System.out.println("Hello from thread number: " + thread.getName());
    }
}
