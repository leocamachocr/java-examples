package com.leoc.threads.lab6part1;

import java.util.Random;

public class Client implements Runnable {

    private final String name;
    private final Supermarket supermarket;

    public Client(String name, Supermarket supermarket) {
        this.supermarket = supermarket;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("Cliente %s llegó al supermercado\n", name);
        //sleep();
        System.out.printf("%s solicita entrar\n", name);
        supermarket.getIn();//espera hasta que el semáforo conceda el acceso
        System.out.printf("%s entró al supermercado\n", name);

        //
        sleep();
        System.out.printf("%s va a salir del supermercado\n", name);
        supermarket.leaving();
        System.out.printf("%s salió del supermercado\n", name);
    }

    private void sleep() {
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException ignored) {
        }
    }
}
