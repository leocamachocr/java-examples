package com.leoc.socket.client.messages;

import java.util.List;

/**
 * Mensaje enviado por el servidor al cliente para mostrar el menú de opciones
 */
public class MenuResponse {

    private List<MenuItem> menu;

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }
}


