package com.codecool.coffee.game;

import java.util.Date;

class Counter {
    private final String name;
    private int value;
    private int lastReportedValue = 0;
    private long lastReportedTime = 0;

    public Counter(String name) {
        this.name = name;
        this.value = 0;
        this.lastReportedTime = new Date().getTime();
    }

    public synchronized void reportRate(Game game) {
        long now = new Date().getTime();
        float rate = (value - lastReportedValue) / (float) (now - lastReportedTime) * 1000;
        game.log(String.format("%s rate: %3.2f / s", name, rate), Colors.GREEN);
        lastReportedValue = value;
        lastReportedTime = now;
    }

    synchronized public void increase() {
        value++;
    }

    synchronized public int getValue() {
        return value;
    }
}
