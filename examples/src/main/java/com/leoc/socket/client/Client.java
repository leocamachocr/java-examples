package com.leoc.socket.client;

import com.leoc.socket.client.messages.*;
import com.leoc.socket.client.util.JsonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Client {

    private final String host;
    private final int port;
    private Socket clientSocket;
    private JsonUtil jsonUtil = new JsonUtil();


    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public MenuResponse requestMenu() throws IOException, ClassNotFoundException {
        try {
            clientSocket = new Socket(host, port);//espera a una conexión por parte del servidor

            Request request = new Request();
            request.setType("MENU");
            send(request);

            MenuResponse menuResponse = receive(MenuResponse.class);
            Request closeRequest = new Request();
            closeRequest.setType("CLOSE");
            send(closeRequest);

            return menuResponse;
        } finally {
            if (clientSocket != null)
                clientSocket.close();
        }
    }


    public void requestOrder(List<MenuItem> menuItems) throws IOException, ClassNotFoundException {
        try {
            clientSocket = new Socket(host, port);

            Request request = new Request();
            request.setType("ORDER");
            send(request);

            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setMenuItems(menuItems);
            send(orderRequest);

            InvoiceResponse invoiceResponse = receive(InvoiceResponse.class);
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setAmount(invoiceResponse.getAmount());
            send(paymentRequest);

            Request closeRequest = new Request();
            closeRequest.setType("CLOSE");
            send(closeRequest);
        } finally {
            if (clientSocket != null)
                clientSocket.close();
        }
    }


    private <T> T receive(Class<T> responseType) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
        return jsonUtil.asObject((String) in.readObject(), responseType);

    }

    private void send(Object request) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        out.writeObject(jsonUtil.asJson(request));

    }


}
