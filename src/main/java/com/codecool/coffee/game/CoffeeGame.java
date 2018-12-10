package com.codecool.coffee.game;

public class CoffeeGame extends Game {
    Counter ordersTaken = new Counter("orders taken");
    Counter coffeesMade = new Counter("coffees made");
    Counter sandwichesMade = new Counter("sandwiches made");
    Action takeOrderAction = new Action("taking order", 1000, 3);
    Action makeCoffeeAction = new Action("making coffee", 1500, 1);
    Action makeSandwichAction = new Action("making sandwich", 2000, 5);

    public CoffeeGame() {
        new Thread(() -> {
            while (true) {
                synchronized (this) {
                    ordersTaken.reportRate(this);
                    coffeesMade.reportRate(this);
                    sandwichesMade.reportRate(this);
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public Order takeOrder() {
        Order order = new Order();
        takeOrderAction.run(this, "#" + order.num);
        ordersTaken.increase();
        return order;
    }

    public void makeCoffee(Order order) {
        synchronized (this) {
            if (order.coffee)
                fatal("trying to make coffee twice");
            order.coffee = true;
        }
        makeCoffeeAction.run(this, "#" + order.num);
        coffeesMade.increase();
    }

    public void makeSandwich(Order order) {
        synchronized (this) {
            if (order.sandwich)
                fatal("trying to make sandwich twice");
            order.sandwich = true;
        }
        makeSandwichAction.run(this, "#" + order.num);
        sandwichesMade.increase();
    }
}
