package com.multithreading.primenumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var listOfNumbers = new ArrayList();
        var rand = new Random();
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            listOfNumbers.add(Math.abs(rand.nextInt()%10));
        }

        System.out.println(listOfNumbers);
        System.out.println(countPrimes(listOfNumbers));
    }

    public static int countPrimes(List<Integer> list) {
        CountPrimes c1 = new CountPrimes(0, list.size()/2, list);
        CountPrimes c2 = new CountPrimes(list.size()/2, list.size(), list);
        Thread firstThread = new Thread(c1), secondThread = new Thread(c2);
        firstThread.start();
        secondThread.start();
        try{
            firstThread.join();
            secondThread.join();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int sum = c1.getCounter() + c2.getCounter();
        return sum;
    }
}
