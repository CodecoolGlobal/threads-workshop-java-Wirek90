package com.codecool.coffee;

import com.codecool.coffee.game.CoffeeGame;
import com.codecool.coffee.game.Order;
import com.codecool.coffee.OrderQueue;

public class CoffeeShop {
    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();
        shop.run();
    }

    private CoffeeGame game = new CoffeeGame();
    private OrderQueue coffeeOrders = new OrderQueue(2);
    private OrderQueue sandwichOrders = new OrderQueue(2);

    public void run() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> this.takeOrders()).start();
        }
        new Thread(() -> this.makeCoffees()).start();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> this.makeSandwiches()).start();
        }
    }

    public void takeOrders() {
        try {
            while (true) {
                Order order = game.takeOrder();
                coffeeOrders.put(order);
                sandwichOrders.put(order);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void makeCoffees() {
        try {
            while (true) {
                Order order = coffeeOrders.take();
                game.makeCoffee(order);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void makeSandwiches() {
        try {
            while (true) {
                Order order = sandwichOrders.take();
                game.makeSandwich(order);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
