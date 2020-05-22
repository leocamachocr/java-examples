package com.leoc.threads.synchronizedobject;

import java.util.Random;

public class AddNumbersToBucket implements Runnable {
    private NumberBucket bucket;

    public AddNumbersToBucket(NumberBucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        for (int count = 1000; count > 0; count--)
            bucket.add(new Random().nextInt(10));
    }
}
