package com.multithreading.hello;

public class JustAThread implements Runnable {

    public JustAThread(String name) {
        Thread.currentThread().setName(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
