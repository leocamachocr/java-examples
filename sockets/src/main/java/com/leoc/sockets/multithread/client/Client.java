package com.leoc.sockets.multithread.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    Socket clientSocket;

    public Client(String server, int port) {
        try {

            clientSocket = new Socket(server, port);//
            Thread.sleep(5000);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject("Este es mi primer mensaje: "+Thread.currentThread().getName());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println(in.readObject());
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
