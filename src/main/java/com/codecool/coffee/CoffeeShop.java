package com.codecool.coffee;

import com.codecool.coffee.game.CoffeeGame;
import com.codecool.coffee.game.Order;

import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CoffeeShop {
    private boolean finished;
    private ArrayList <Thread> threads = new ArrayList<>();
    public static void main(String[] args) {
        CoffeeShop shop = new CoffeeShop();
        shop.run();
    }

    private CoffeeGame game = new CoffeeGame();
    private OrderQueue coffeeOrders = new OrderQueue(10);
    private OrderQueue sandwichOrders = new OrderQueue(10);

    public void run() {
        finished = false;
        startCashRegisters(3);
        startCoffeeMachine(1);
        startTosters(5);

        try {
            Thread.sleep(10000);
            game.log("Closing shop...");
            finished = true;
            for (Thread t : threads) {
                t.join();
            }
            System.exit(0);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public void startCashRegisters(int numOfashRegisters) {
        for (int i = 0 ; i < numOfashRegisters; i++) {
            Thread cashMachineThread = new Thread( () -> {
                takeOrders();

            });
            cashMachineThread.start();
            threads.add(cashMachineThread);
        }
    }

    public void takeOrders() {
        while (!finished) {
            Order order = game.takeOrder();
            try {
                coffeeOrders.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sandwichOrders.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void startCoffeeMachine(int numOfCoffeeMachines) {
        for (int i = 0; i < numOfCoffeeMachines; i++) {
            Thread coffeeThread = new Thread(   () -> {
                prepareCoffee();
            });
            coffeeThread.start();
            threads.add(coffeeThread);
        }
    }


    public void prepareCoffee() {
        while (!finished) {
            try {
                Order order = coffeeOrders.take();
                if (order != null) {
                    game.makeCoffee(order);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void  startTosters(int numOfTosters) {
        for (int i = 0; i < numOfTosters ; i++) {
            Thread sandwichThread = new Thread(  () ->{
                prepareSandwiches();

            });
            sandwichThread.start();
            threads.add(sandwichThread);
        }
    }


    public void prepareSandwiches() {
         while(!finished) {
             try {
                 Order order = sandwichOrders.take();
                 if (order != null) {
                     game.makeSandwich(order);
                 }

             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }

    }


}
