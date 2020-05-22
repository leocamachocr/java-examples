package com.leoc.threads.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Race {
    private Racer racer1;
    private Racer racer2;
    private Racer racer3;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        System.out.println("Todos los corredores terminaron");
    });

    public static void main(String args[]) {
        Race race = new Race();
        race.prepareRacer();
        race.startRace();
    }

    public void prepareRacer() {
        cyclicBarrier = new CyclicBarrier(4);
        racer1 = new Racer("La Pulga", cyclicBarrier);
        racer2 = new Racer("El Fenómeno", cyclicBarrier);
        racer3 = new Racer("El Bicho", cyclicBarrier);
    }

    public void startRace() {
        ExecutorService threadExecutor =
                Executors.newFixedThreadPool(10);
        if (!cyclicBarrier.isBroken()) {
            threadExecutor.execute(racer1);
            threadExecutor.execute(racer2);
            threadExecutor.execute(racer3);
            //threadExecutor.shutdown()
            try {
                System.out.println("Ready...");
                Thread.sleep(1000);
                System.out.println("3");
                Thread.sleep(1000);
                System.out.println("2");
                Thread.sleep(1000);
                System.out.println("1");
                Thread.sleep(1000);
                System.out.println("Go!!");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
