package com.leoc.threads.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Postman implements Runnable {
    private BlockingQueue<String> letters;
    private Random random = new Random();

    public Postman(BlockingQueue<String> letters) {
        this.letters = letters;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String letter = letters.take();
                System.out.printf("%s: Entregando carta %s, pendientes(%d)\n", Thread.currentThread().getName(),
                        letter, letters.size());
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
