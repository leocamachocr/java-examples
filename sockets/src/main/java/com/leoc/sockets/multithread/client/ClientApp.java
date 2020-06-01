package com.leoc.sockets.multithread.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            Client client = new Client("127.0.0.1", 5454);
        });
        executorService.submit(() -> {
            Client client = new Client("127.0.0.1", 5454);
        });
    }
}
