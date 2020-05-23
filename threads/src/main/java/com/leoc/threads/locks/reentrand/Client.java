package com.leoc.threads.locks.reentrand;

import java.util.Random;

public class Client implements Runnable {
    private final Bar bar;
    private final String name;
    private final Random random = new Random();

    public Client(String name, Bar bar) {
        this.name = name;
        this.bar = bar;
    }

    @Override
    public void run() {
        System.out.printf("%s: is in the bar\n", name);
        while (true) {
            try {
                Thread.sleep(random.nextInt(10000));
                System.out.printf("%s: may I have a beer?\n", name);
                bar.getBeer(this);
            } catch (InterruptedException ignored) {
            }

        }
    }

    public String getName() {
        return name;
    }
}
