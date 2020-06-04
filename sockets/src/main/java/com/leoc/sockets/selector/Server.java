package com.leoc.sockets.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();
        System.out.println("Selector open: " + selector.isOpen());
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 5454);
        serverSocketChannel.bind(hostAddress);
        serverSocketChannel.configureBlocking(false);
        int ops = serverSocketChannel.validOps();
        serverSocketChannel.register(selector, ops, null);

        while (true) {
            System.out.println("Esperando seleccionar una conexión...");
            int numberOfKeys = selector.select();
            System.out.println("Número de keys: " + numberOfKeys);
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel client = ((ServerSocketChannel) selectionKey.channel()).accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("Nueva conexión aceptada: " + client);
                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);
                    String message = new String(buffer.array()).trim();
                    Thread.sleep(5000);
                    System.out.println("Mensaje del cliente: " + message);
                    buffer.flip();
                    client.write(buffer);
                    buffer.clear();
                    if (message.equals("Bye.")) {
                        client.close();//Siempre se debe finalizar la conexión
                        System.out.println("Mensaje se ha terminado de transmitir; cerrado.");
                    }
                }

                iterator.remove();

            } // end while loop

        } // end for loop
    }
}