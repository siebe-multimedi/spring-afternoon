package com.example.afternoon.cycle;

/**
 * Exercise 1. Stock minus what is already ordered.
 */
public class InventoryChecker {

    private static final int STOCK = 10;

    private final OrderProcessor orders;

    public InventoryChecker(OrderProcessor orders) {
        this.orders = orders;
    }

    public int available(String sku) {
        return STOCK - orders.pendingFor(sku);
    }
}
