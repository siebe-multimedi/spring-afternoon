package com.example.afternoon.cycle;

import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 1 (solved). The shared state that both classes really needed: what is
 * already ordered. Extracting it breaks the cycle, because neither OrderProcessor
 * nor InventoryChecker needs the other one anymore for this.
 */
public class PendingOrders {

    private final Map<String, Integer> quantities = new HashMap<>();

    public void add(String sku, int quantity) {
        quantities.merge(sku, quantity, Integer::sum);
    }

    public int quantityFor(String sku) {
        return quantities.getOrDefault(sku, 0);
    }
}
