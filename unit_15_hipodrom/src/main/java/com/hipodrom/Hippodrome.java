package com.hipodrom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Hippodrome {
    private  List<String> horseList = new ArrayList<>();
    private  CountDownLatch countDownLatch = new CountDownLatch(10);

    public  synchronized void finish(String horseNumber) {
        horseList.add(horseNumber);
        countDownLatch.countDown();
    }

    public  void startCompetition(int horseNumber) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Horse(i, this)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Your horse finished - " + (horseList.indexOf("Thread-" + horseNumber) + 1));
    }
}
