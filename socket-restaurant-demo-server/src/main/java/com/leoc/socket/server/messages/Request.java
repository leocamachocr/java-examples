package com.leoc.socket.server.messages;

/**
 * Mensaje enviado por el Cliente al Servidor para hacer una solicitud
 */
public class Request {

    /**
     * Tipos
     * - MENU : Solicita el menu
     * - ORDER : Inicia la solicitud de la orden
     * - CLOSE : Cierra la conexión, finaliza la interacción con el servidor
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
