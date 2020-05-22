package com.leoc.threads.scheduled;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPool {
    private static ExecutorService executorService;
    private static ScheduledExecutorService scheduledExecutorService;

    public static ExecutorService getPool() {
        if (executorService == null) {
            executorService = Executors.newCachedThreadPool();
        }
        return executorService;
    }

    public static ScheduledExecutorService getScheduledPool() {
        if (scheduledExecutorService == null) {
            scheduledExecutorService = Executors.newScheduledThreadPool(10);
        }
        return scheduledExecutorService;
    }
}
