package com.codecool.coffee;

import com.codecool.coffee.game.Order;

import java.util.LinkedList;
import java.util.List;
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
    public synchronized void put(Order order) throws InterruptedException {
        if (capacity > 0) {
            while (queue.size() == capacity)
                wait();
        }
        queue.add(order);
        notifyAll();
    }

    /**
     * Removes an order from the queue. Waits if the queue is empty.
     * @return
     * @throws InterruptedException if the thread has been interrupted while waiting
     */
    public synchronized Order take() throws InterruptedException {
        while (queue.isEmpty())
            wait();
        Order order = queue.remove();
        notifyAll();
        return order;
    }
}
