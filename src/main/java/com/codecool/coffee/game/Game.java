package com.codecool.coffee.game;

import java.util.Date;

class Game {
    private long startTime;

    public Game() {
        startTime = new Date().getTime();
        log("Starting");
    }

    public synchronized void log(String message, String colorCode) {
        long interval = new Date().getTime() - startTime;
        System.out.println(
                String.format("[%.2f s] [%s] %s",
                        interval / 1000f,
                        Thread.currentThread().getName(),
                        colorCode + message + Colors.RESET)
        );
    }

    public void log(String message) {
        log(message, Colors.RESET);
    }

    public void fatal(String message) {
        log(message, Colors.RED);
        System.exit(1);
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
