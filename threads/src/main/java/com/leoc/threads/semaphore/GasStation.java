package com.leoc.threads.semaphore;

import java.util.concurrent.Semaphore;

public class GasStation {
    private Semaphore semaphore;

    public GasStation(int availableSlots) {
        semaphore = new Semaphore(availableSlots);
    }

    public void gettingInLine() {
        try {
            semaphore.acquire();
        } catch (InterruptedException ignored) {
        }
    }

    public void leavingGasStation() {
        semaphore.release();
    }
}

