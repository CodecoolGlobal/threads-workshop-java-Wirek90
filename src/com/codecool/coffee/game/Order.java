package com.codecool.coffee.game;

public class Order {
    static int counter = 0;

    final int num;
    boolean coffee = false;
    boolean sandwich = false;
    Order() {
        synchronized (Order.class) {
            this.num = counter;
            counter++;
        }
    }

}
