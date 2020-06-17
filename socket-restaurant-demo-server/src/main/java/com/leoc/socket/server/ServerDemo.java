package com.leoc.socket.server;

import com.leoc.socket.server.messages.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ServerDemo {
    public static void main(String[] args) {
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem(1, "Papas", 1_000));
        menu.add(new MenuItem(2, "Hamburguesas", 1_000));
        menu.add(new MenuItem(3, "Pinto", 1_000));
        menu.add(new MenuItem(4, "Sandwich de Pollo", 1_000));
        menu.add(new MenuItem(5, "Empanada arreglada de carne", 1_000));
        menu.add(new MenuItem(6, "Salchi-papas", 1_000));
        menu.add(new MenuItem(7, "Patacones", 1_000));

        Server server = new Server(10014, menu);
    }
}
