package com.leoc.threads.futureexample;

import java.util.concurrent.*;

public class Race {
    private Racer racer1, racer2, racer3;

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        Race race = new Race();
        race.prepareRacer();
        race.startRace();
    }

    public void prepareRacer() {
        racer1 = new Racer("La Pulga");
        racer2 = new Racer("El Fenómeno");
        racer3 = new Racer("El Bicho");
    }

    public void startRace() throws ExecutionException, InterruptedException {
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        Future<Long> future1 = threadExecutor.submit(racer1);
        Future<Long> future2 = threadExecutor.submit(racer2);
        Future<Long> future3 = threadExecutor.submit(racer3);

        /*...*/

        try {
            System.out.println("Racer 1: " + future1.get(1, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            System.out.println("Racer 1 no terminó la carrera");
        }

        System.out.println("Racer 2: " + future2.get());
        System.out.println("Racer 3: " + future3.get());
        threadExecutor.shutdown();
    }
}
