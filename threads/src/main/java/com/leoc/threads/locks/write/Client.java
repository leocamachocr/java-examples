package com.leoc.threads.locks.write;

import java.util.Random;
import static com.leoc.util.Output.ANSI_RESET;

public class Client implements Runnable {
    private final Bar bar;
    private final String name;
    private final Random random = new Random();
    private final String color;

    public Client(String name, String color, Bar bar) {
        this.name = name;
        this.bar = bar;
        this.color = color;
    }

    @Override
    public void run() {
        System.out.printf("%s%s: is in the bar\n%s", color, name, ANSI_RESET);
        while (true) {
            try {
                Thread.sleep(random.nextInt(10000));
                System.out.printf("%s%s: may I have a beer?\n%s", color, name, ANSI_RESET);
                bar.getBeer(this);
                if (random.nextBoolean()) {
                    System.out.printf("%s%s Can you give me the invoice?\n%s", color, name, ANSI_RESET);
                    bar.pay(this);
                }
            } catch (InterruptedException ignored) {
            }

        }
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
