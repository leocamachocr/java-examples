package com.leoc.threads.locks.write;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.leoc.util.Output.ANSI_RESET;

public class Bar {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Random random = new Random();

    public void getBeer(Client client) {
        try {
            lock.readLock().lock();
            System.out.printf("%sBar: Off course %s\n%s", client.getColor(), client.getName(), ANSI_RESET);
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException ignored) {

        } finally {
            System.out.printf("%sBar: %s take your beer\n%s", client.getColor(), client.getName(), ANSI_RESET);
            lock.readLock().unlock();
        }
    }

    public void pay(Client client) {
        try {
            lock.writeLock().lock();
            System.out.printf("%sBar:Sure take your invoice %s\n%s", client.getColor(), client.getName(), ANSI_RESET);
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException ignored) {

        } finally {
            System.out.printf("%sBar: %s Thanks see you soon!!\n%s",
                    client.getColor(), client.getName(), ANSI_RESET);
            lock.writeLock().unlock();
        }
    }
}
