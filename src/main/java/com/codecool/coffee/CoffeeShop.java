package com.codecool.coffee;

import com.codecool.coffee.game.CoffeeGame;
import com.codecool.coffee.game.Order;

public class CoffeeShop {
    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();
        shop.run();
    }

    private CoffeeGame game = new CoffeeGame();

    public void run() {
        while (true) {
            Order order = game.takeOrder();

            // Don't make coffee yet
            // com.codecool.coffee.game.makeCoffee(order);

            // Don't make sandwich yet
            // com.codecool.coffee.game.makeSandwich(order);
        }
    }
}
