package com.leoc.threads.scheduled;

public class TrainDemo {
    public static void main(String[] args) {

        new Train().start();
        ThreadPool.getPool().shutdownNow();
        ThreadPool.getScheduledPool().shutdown();
    }
}

