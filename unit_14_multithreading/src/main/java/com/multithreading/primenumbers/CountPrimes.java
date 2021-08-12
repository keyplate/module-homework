package com.multithreading.primenumbers;

import java.util.List;

public class CountPrimes implements Runnable {
    private int fromId;
    private int toId;
    private List<Integer> listOfNumbers;
    private int counter;

    public CountPrimes(int fromId, int toId, List<Integer> listToCount) {
        this.fromId = fromId;
        this.toId = toId;
        this.listOfNumbers = listToCount;
        this.counter = 0;
    }

    @Override
    public void run() {
        for (int i = fromId; i < toId; i++) {
            if(isPrime(listOfNumbers.get(i))){
                counter++;
            }
        }
    }

    public int getCounter() {
        return counter;
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        } else {
            for (int i = 2; i <= number / 2; i++) {
                if ((number % i) == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
