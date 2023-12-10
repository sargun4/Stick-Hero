package com.application.stickhero;

public class GameProgress {
    private static int lastScore;
    private static int highestScore;
    private int totalCherries;
    private int money;

    public GameProgress() {
        this.lastScore = 0;
        this.highestScore = 0;
        this.totalCherries = 0;
        this.money = 100;
    }

    public static int getLastScore() {
        return lastScore;
    }

    public static void setLastScore(int lastScore) {
        GameProgress.lastScore = lastScore;
    }

    public static int getHighestScore() {
        return highestScore;
    }

    public static void setHighestScore(int highestScore) {
        GameProgress.highestScore = highestScore;
    }

    public int getTotalCherries() {
        return totalCherries;
    }

    public void setTotalCherries(int totalCherries) {
        this.totalCherries = totalCherries;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void updateGameProgress(int newLastScore, int newHighestScore, int newTotalCherries, int newMoneyValue) {
        lastScore = newLastScore;
        highestScore = newHighestScore;
        totalCherries = newTotalCherries;
        money = newMoneyValue;
    }
}
