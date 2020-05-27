package com.leoc.threads.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Racer implements Runnable {
    private String name;
    private CyclicBarrier cyclicBarrier;

    public Racer(String name, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
            System.out.printf("%s starts to run\n", name);

            Thread.sleep(new Random().nextInt(5000));
            System.out.println(String.format("%s ends to run", name));
        } catch (InterruptedException | BrokenBarrierException ignored) {
            ignored.printStackTrace();
        }
    }
}
