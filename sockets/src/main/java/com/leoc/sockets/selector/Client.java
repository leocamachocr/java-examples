package com.leoc.sockets.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private SocketChannel channel;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            Client client = new Client();
            try {
                client.connect();
                client.send("My name is Athos");
                Thread.sleep(5000);
                client.send("Bye.");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
      /*  executor.execute(() -> {
            Client client = new Client();
            try {
                client.connect();
                client.send("My name is Phortos");
                Thread.sleep(5000);
                client.send("Bye.");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        executor.execute(() -> {
            Client client = new Client();
            try {
                client.connect();
                client.send("My name is Aramys");
                Thread.sleep(5000);
                client.send("Bye.");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });*/
        executor.shutdown();

    }

    private void close() throws IOException {
        channel.close();
    }

    private void send(String message) throws IOException {
        byte[] raw = message.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(raw);
        channel.write(buffer);
        System.out.println(message);
        buffer.clear();
        channel.read(buffer);
        String response = new String(buffer.array()).trim();
        System.out.println("response=" + response);
        buffer.clear();
    }

    private void connect() throws IOException {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);
        channel = SocketChannel.open(hostAddress);

    }
}
