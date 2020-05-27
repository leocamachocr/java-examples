package com.leoc.threads.countdown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Racer implements Runnable {
    private String name;
    private CountDownLatch countDownLatch;

    public Racer(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        countDownLatch.countDown();
        try {
            countDownLatch.await();
            System.out.printf("%s starts to run\n", name);
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {/*TODO imprimir exception*/}
        System.out.println(String.format("%s ends to run", name));
    }
}
