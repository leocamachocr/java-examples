package com.leoc.threads.futureexample;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Callable;

import static java.lang.String.format;

public class Racer implements Callable<Long> {
    private String name;

    public Racer(String name) {
        this.name = name;
    }

    @Override
    public Long call() throws InterruptedException {
        long initialTime = Instant.now().getEpochSecond();
        System.out.println(format("%s starts to run", name));
        Thread.sleep(new Random().nextInt(5000));
        System.out.println(format("%s ends to run", name));
        return (Instant.now().getEpochSecond() - initialTime);
    }
}
