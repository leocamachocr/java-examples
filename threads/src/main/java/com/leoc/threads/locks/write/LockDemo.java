package com.leoc.threads.locks.write;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.leoc.util.Output.*;

public class LockDemo {
    public static void main(String[] args) {
        Bar bar = new Bar();
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Client("John",ANSI_RED, bar));
        service.submit(new Client("Dave",ANSI_BLUE, bar));
        service.submit(new Client("Gabe",ANSI_YELLOW, bar));
        service.submit(new Client("Robi",ANSI_GREEN, bar));
        service.submit(new Client("Marg",ANSI_CYAN, bar));
        service.submit(new Client("Till",ANSI_PURPLE, bar));
    }
}
