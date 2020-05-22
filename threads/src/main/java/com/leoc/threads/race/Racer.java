package com.leoc.threads.race;

import java.util.Random;

//import static java.lang.String.format;
public class Racer implements Runnable {
    private String name;

    public Racer(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        System.out.printf("%s starts to run\n", name);

        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(String.format("%s ends to run", name));
    }
}
