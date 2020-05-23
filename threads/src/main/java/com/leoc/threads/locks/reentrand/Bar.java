package com.leoc.threads.locks.reentrand;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is a demo
 *
 */
public class Bar {
    private Lock lock = new ReentrantLock();
    private Random random = new Random();

    public void getBeer(Client client) {
        try {
            lock.lock();
            System.out.printf("Bar: Off course %s\n", client.getName());
            Thread.sleep(random.nextInt(2000));
        }catch (InterruptedException ignored){

        }finally {
            System.out.printf("Bar: %s take your beer\n", client.getName());
            lock.unlock();
        }
    }
}
