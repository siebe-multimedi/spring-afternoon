package com.example.afternoon.lazy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Exercise 4. Pretend creating this bean is expensive. CREATED counts how many
 * instances were ever constructed, so a test can see WHEN that happens.
 *
 * Note: this class has no @Component. The tests register it themselves, so it
 * does not end up in the full application context of the other exercises.
 */
public class HeavyService {

    public static final AtomicInteger CREATED = new AtomicInteger();

    public HeavyService() {
        CREATED.incrementAndGet();
    }

    public String work() {
        return "done";
    }
}
