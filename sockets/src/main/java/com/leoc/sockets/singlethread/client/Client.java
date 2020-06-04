package com.leoc.sockets.singlethread.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    Socket clientSocket;

    public Client(String server, int port) {
        try {
            clientSocket = new Socket(server, port);
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject("Este es mi primer mensaje 2");
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println(in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
