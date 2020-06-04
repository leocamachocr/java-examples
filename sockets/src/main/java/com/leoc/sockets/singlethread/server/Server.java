package com.leoc.sockets.singlethread.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket;

    public Server(int port) {
        try {
            //aqui
            serverSocket = new ServerSocket(port);
            System.out.println("Esperando conexión");//
            while(true) {
                Socket socket = serverSocket.accept();//esperando a que llegue una conexión
                System.out.println("Conexión recibida");//
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                String message = (String) in.readObject();
                System.out.println("Message Recibido: " + message);//
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

                out.writeObject("Mensaje Recibido: " + message);
                System.out.println("Mensaje Enviado:" + message);//
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();//Puede lanzar una excepción puesto que el puerto puede estar ocupado
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
