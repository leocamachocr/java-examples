package com.leoc.threads.lab6part1;

import java.util.concurrent.Semaphore;

public class Supermarket {
    private Semaphore semaphore;

    public Supermarket(int availableSlots) {
        semaphore = new Semaphore(availableSlots);
    }

    public void getIn() {
        try {
            semaphore.acquire();
        } catch (InterruptedException ignored) {
        }
    }

    public void leaving() {
        semaphore.release();
    }
}
