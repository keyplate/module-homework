package com.hipodrom;

import java.util.Random;


public class Horse implements Runnable {
    private long distance;

    public Horse(long distance, int numberOfHorse) {
        this.distance = distance;
        Thread.currentThread().setName(String.valueOf(numberOfHorse));
    }

    @Override
    public void run() {
        Random rand = new Random();
        for (int i = 0; i < distance;) {
            i += rand.nextInt()%100+100;
            if(rand.nextBoolean()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Hippodrome.finish(Thread.currentThread().getName());
    }
}
