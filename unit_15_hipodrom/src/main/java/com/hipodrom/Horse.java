package com.hipodrom;

import java.util.Random;


public class Horse implements Runnable {
    private Hippodrome hippodrome;
    public Horse(int numberOfHorse, Hippodrome hippodrome) {
        Thread.currentThread().setName(String.valueOf(numberOfHorse));
        this.hippodrome = hippodrome;
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < 1000;) {
            i += rand.nextInt()%100+100;
            if(rand.nextBoolean()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        hippodrome.finish(Thread.currentThread().getName());
    }
}
