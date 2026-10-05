package com.example.afternoon.lazy;

import org.springframework.context.annotation.Lazy;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Exercise 4 (solved). @Lazy means: do not create this singleton at startup, only
 * when something asks for it for the first time.
 */
@Lazy
public class HeavyService {

    public static final AtomicInteger CREATED = new AtomicInteger();

    public HeavyService() {
        CREATED.incrementAndGet();
    }

    public String work() {
        return "done";
    }
}
