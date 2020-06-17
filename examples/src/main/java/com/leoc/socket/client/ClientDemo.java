package com.leoc.socket.client;

import com.leoc.socket.client.messages.MenuItem;
import com.leoc.socket.client.messages.MenuResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client("127.0.0.1", 10014);
        MenuResponse menuResponse = client.requestMenu();
        List<MenuItem> order = new ArrayList<MenuItem>();
        int max = 4;
        Random random = new Random();
        for (MenuItem item : menuResponse.getMenu()) {
            if (random.nextBoolean()) {
                order.add(item);
                max--;
            }
            if (max == 0) break;
        }

        client.requestOrder(order);
    }
}
