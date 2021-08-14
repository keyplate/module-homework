package com.multithreading.hello;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Thread> threadPool = new Stack<>();
        Thread tmp = null;
        for (int i = 0; i < 50; i++) {
            tmp = new Thread(new JustAThread(String.valueOf(i)));
            tmp.setName("" + i);
            threadPool.push(tmp);
        }
        for (int i = 0; i < 50; i++) {
            Thread lastThread = threadPool.pop();
            lastThread.start();
            try {
                lastThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
