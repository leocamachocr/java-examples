package com.leoc.threads.phase;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

public class Racer implements Runnable {
    private String name;
    private Phaser phaser;

    public Racer(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
        phaser.register();
    }
    @Override
    public void run() {
        System.out.printf("%s ready to run\n", name);

        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s starts to run\n", name);
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {/*TODO imprimir exception*/}
        System.out.println(String.format("%s ends to run", name));
        phaser.arriveAndDeregister();
    }
}
