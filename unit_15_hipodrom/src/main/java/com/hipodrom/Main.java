package com.hipodrom;

public class Main {
    public static void main(String[] args) {
        Hippodrome hippodrome = new Hippodrome();
        System.out.println("Your wager placed on horse number " + args[0]);
        hippodrome.startCompetition(Integer.parseInt(args[0]));
    }
}
