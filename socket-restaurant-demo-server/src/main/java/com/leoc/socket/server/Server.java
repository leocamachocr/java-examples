package com.leoc.socket.server;

import com.leoc.socket.server.messages.*;
import com.leoc.socket.server.util.JsonUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private JsonUtil jsonUtil = new JsonUtil();
    private ServerSocket serverSocket = null;
    private List<MenuItem> menu;

    public Server(int port, List<MenuItem> menu) {
        this.menu = menu;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Esperando conexión");//
            while (true) {
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
                System.out.println("Conexión recibida");//

                Request request = receive(Request.class, socket);
                System.out.println("Message Recibido: " + jsonUtil.asJson(request));//
                if (request.getType().equals("MENU")) {
                    request = processMenuRequest(socket);
                } else if (request.getType().equals("ORDER")) {
                    request = processOrderRequest(socket);
                }
                if (request.getType().equals("CLOSE")) {
                    socket.close();
                }

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private Request processOrderRequest(Socket socket) throws IOException, ClassNotFoundException {
        OrderRequest orderRequest = receive(OrderRequest.class, socket);
        System.out.println("Recibiendo la orden:");
        System.out.println(jsonUtil.asJson(orderRequest.getMenuItems()));//Solo para ejemplificar que recibimos una opcion de menu
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setAmount(10_000);//modificar sumando las opciones de menu
        send(invoiceResponse, socket);
        PaymentRequest paymentRequest = receive(PaymentRequest.class, socket);
        System.out.println("Pago Recibido");
        System.out.println(jsonUtil.asJson(paymentRequest));
        return receive(Request.class, socket);

    }

    private Request processMenuRequest(Socket socket) throws IOException, ClassNotFoundException {
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setMenu(menu);
        send(menuResponse, socket);
        return receive(Request.class, socket);
    }

    private <T> T receive(Class<T> responseType, Socket socket) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return jsonUtil.asObject((String) in.readObject(), responseType);

    }

    private void send(Object request, Socket socket) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(jsonUtil.asJson(request));
    }

}
