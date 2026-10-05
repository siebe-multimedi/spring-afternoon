package com.example.afternoon.cycle;

/**
 * Exercise 1 (solved).
 */
public class OrderProcessor {

    private final InventoryChecker inventory;
    private final PendingOrders pending;

    public OrderProcessor(InventoryChecker inventory, PendingOrders pending) {
        this.inventory = inventory;
        this.pending = pending;
    }

    public boolean place(String sku, int quantity) {
        if (inventory.available(sku) < quantity) {
            return false;
        }
        pending.add(sku, quantity);
        return true;
    }
}
