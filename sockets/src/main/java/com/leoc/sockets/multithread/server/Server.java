package com.leoc.sockets.multithread.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Esperando conexión");
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
                Thread.sleep(5000);
                System.out.println("Conexión recibida");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String message = (String) in.readObject();
                System.out.println("Message Received: " + message);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                System.out.println("Mensaje recibido:" + message);
                out.writeObject("Mensaje Recibido: " + message);
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }

    }
}
