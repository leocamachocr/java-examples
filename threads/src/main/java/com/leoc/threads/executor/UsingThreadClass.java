package com.leoc.threads.executor;

public class UsingThreadClass {

    public static void main(String args[]) {
        Thread racer1 = new Thread(new Racer("El Kaiser"));
        Thread racer2 = new Thread(new Racer("O Rei"));
        racer1.start();
        racer2.start();
    }
}
