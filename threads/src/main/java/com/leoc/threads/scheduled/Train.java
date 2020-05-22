package com.leoc.threads.scheduled;

import java.util.Random;

public class Train {
    public void start() {
        ThreadPool.getScheduledPool().execute(this::chuchu);
        new TicketChequer().start();
    }

    private void chuchu() {
        int duration = new Random().nextInt(10) + 5;
        System.out.printf("El tren esta arrancando el viaje durará %d segundos \n", duration);
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El viaje a terminado, vuelva pronto");
    }
}
