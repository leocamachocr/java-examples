package com.leoc.threads.synchronizedobject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedObject {

    public static void main(String[] args) throws InterruptedException {
        NumberBucket bucket = new NumberBucket();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new AddNumbersToBucket(bucket));
        service.execute(new AddNumbersToBucket(bucket));
        // do {
        Thread.sleep(5000);
        System.out.println(bucket.size());
        //}while (true);
        service.shutdown();
    }
}
