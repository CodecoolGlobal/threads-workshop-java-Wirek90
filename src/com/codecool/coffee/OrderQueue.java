package com.codecool.coffee;

import com.codecool.coffee.game.Order;

import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private Queue<Order> queue = new LinkedList<>();
    // 0 if the queue can grow without limit
    private int capacity;

    public OrderQueue() {
        this(0);
    }

    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Adds an order to the queue. Waits if the queue is full.
     * @param order
     * @throws InterruptedException if the thread has been interrupted while waiting
     */
    public void put(Order order) throws InterruptedException {
        // TODO
    }

    /**
     * Removes an order from the queue. Waits if the queue is empty.
     * @return order removed from the queue
     * @throws InterruptedException if the thread has been interrupted while waiting
     */
    public Order take() throws InterruptedException {
        // TODO
        return null;
    }
}
