package com.leoc.threads.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        GasStation gasStation = new GasStation(1);
        service.submit(new Car("1", gasStation));
        service.submit(new Car("2", gasStation));
        service.submit(new Car("3", gasStation));
        service.shutdown();
    }
}
