package com.leoc.threads.phase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class Race {
    private Racer racer1;
    private Racer racer2;
    private Racer racer3;
    private Phaser phaser;

    public static void main(String args[]) {
        Race race = new Race();
        race.prepareRacer();
        race.startRace();
    }

    public void prepareRacer() {
        phaser = new Phaser(1);
        racer1 = new Racer("La Pulga", phaser);
        racer2 = new Racer("El Fenómeno", phaser);
        racer3 = new Racer("El Bicho", phaser);
    }

    public void startRace() {
        ExecutorService threadExecutor =
                Executors.newFixedThreadPool(10);

        System.out.printf("Phase %d starts\n", phaser.getPhase());
        threadExecutor.execute(racer1);
        threadExecutor.execute(racer2);
        threadExecutor.execute(racer3);
        phaser.arriveAndAwaitAdvance();

        System.out.printf("Phase %d starts\n", phaser.getPhase());
        threadExecutor.execute(new Racer("Batman", phaser));
        threadExecutor.execute(new Racer("Joker", phaser));
        phaser.arriveAndAwaitAdvance();
    }
}
