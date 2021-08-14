package com.hipodrom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Hippodrome {
    private static List<String> horseList = new ArrayList<>();
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static synchronized void finish(String horseNumber) {
        horseList.add(horseNumber);
        countDownLatch.countDown();
    }

    public static void startCompetition(int horseNumber) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Horse(1000, i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(horseList.stream()
                .filter(x -> x.equals(String.valueOf(horseNumber)))
                .findAny()
                .orElse(null));
    }
}
