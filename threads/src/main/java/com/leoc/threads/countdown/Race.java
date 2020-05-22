package com.leoc.threads.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Race {
    private Racer racer1;
    private Racer racer2;
    private Racer racer3;
    private CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String args[]) {
        Race race = new Race();
        race.prepareRacer();
        race.startRace();
    }

    public void prepareRacer() {
        countDownLatch = new CountDownLatch(3);
        racer1 = new Racer("La Pulga", countDownLatch);
        racer2 = new Racer("El Fenómeno", countDownLatch);
        racer3 = new Racer("El Bicho", countDownLatch);
    }

    public void startRace() {
        ExecutorService threadExecutor =
                Executors.newFixedThreadPool(10);
        threadExecutor.execute(racer1);
        threadExecutor.execute(racer2);
        threadExecutor.execute(racer3);
        threadExecutor.shutdown();
        try {
            countDownLatch.await();
            System.out.println("Todos los corredores terminaron la tarea");
        } catch (InterruptedException e) {/*TODO manejar exception*/}
    }
}
