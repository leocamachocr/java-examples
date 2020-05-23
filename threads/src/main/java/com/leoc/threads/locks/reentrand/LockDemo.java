package com.leoc.threads.locks.reentrand;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockDemo {
    public static void main(String[] args) {
        Bar bar = new Bar();
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Client("John", bar));
        service.submit(new Client("Dave", bar));
        service.submit(new Client("Gabe", bar));
        service.submit(new Client("Robi", bar));
        service.submit(new Client("Marg", bar));
        service.submit(new Client("Till", bar));
    }
}
