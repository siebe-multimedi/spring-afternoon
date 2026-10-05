package com.example.afternoon.cycle;

/**
 * Exercise 1 (solved).
 */
public class InventoryChecker {

    private static final int STOCK = 10;

    private final PendingOrders pending;

    public InventoryChecker(PendingOrders pending) {
        this.pending = pending;
    }

    public int available(String sku) {
        return STOCK - pending.quantityFor(sku);
    }
}
