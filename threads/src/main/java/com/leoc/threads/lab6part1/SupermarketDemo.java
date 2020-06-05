package com.leoc.threads.lab6part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SupermarketDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Supermarket supermarket = new Supermarket(1);
        service.submit(new Client("Pepe", supermarket));
        service.submit(new Client("Lucho", supermarket));
        service.submit(new Client("Clementina", supermarket));
        service.shutdown();
    }
}
