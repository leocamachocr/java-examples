package com.leoc.threads.semaphore;

import java.util.Random;

public class Car implements Runnable {
    private final String name;
    private GasStation gasStation;

    public Car(String name, GasStation gasStation) {
        this.gasStation = gasStation;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("Car %s is moving\n", name);
        sleep();
        System.out.printf("%s needs fuel\n", name);
        gasStation.gettingInLine();
        System.out.printf("%s is getting gasoline\n", name);
        sleep();
        System.out.printf("%s is on the road\n", name);
        gasStation.leavingGasStation();
    }

    private void sleep() {
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException ignored) {
        }
    }
}
