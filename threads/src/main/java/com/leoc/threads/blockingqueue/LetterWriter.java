package com.leoc.threads.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class LetterWriter implements Runnable {
    private BlockingQueue<String> letters;
    private int count;
    private Random random = new Random();

    public LetterWriter(BlockingQueue<String> letters) { this.letters = letters; }
    @Override
    public void run() {

        while (true) {
            String letter = count++ + "_" + Thread.currentThread().getName();
            System.out.printf("Creando carta %s\n",letter);
            letters.add(letter);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
