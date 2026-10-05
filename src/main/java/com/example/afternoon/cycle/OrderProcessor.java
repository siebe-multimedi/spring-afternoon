package com.example.afternoon.cycle;

import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 1. Places orders, but only when enough stock is available.
 * No @Component on purpose: the test registers the classes itself.
 */
public class OrderProcessor {

    private final InventoryChecker inventory;
    private final Map<String, Integer> pending = new HashMap<>();

    public OrderProcessor(InventoryChecker inventory) {
        this.inventory = inventory;
    }

    public boolean place(String sku, int quantity) {
        if (inventory.available(sku) < quantity) {
            return false;
        }
        pending.merge(sku, quantity, Integer::sum);
        return true;
    }

    public int pendingFor(String sku) {
        return pending.getOrDefault(sku, 0);
    }
}
