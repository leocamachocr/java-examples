package com.leoc.threads.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class MailDemo {
    public static void main(String[] args) {
        BlockingQueue<String> letters = new LinkedBlockingQueue<>();
        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(new LetterWriter(letters));
        service.submit(new Postman(letters));
        service.submit(new Postman(letters));
        service.submit(new Postman(letters));
        service.shutdown();

    }
}
