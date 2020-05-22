package com.leoc.threads.scheduled;

import java.util.concurrent.TimeUnit;

public class TicketChequer {
    public void start() {
        ThreadPool.getScheduledPool().schedule(() ->
                System.out.println("Revisando tiquetes"), 2, TimeUnit.SECONDS);
    }
}
