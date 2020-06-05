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
            System.out.println("Número de solicitudes: " + numberOfKeys);
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {//puede ser un for para simplemente iterar la lista ejemplo: for(SelectionKey selectionKey:selectedKeys)
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
                    Thread.sleep(5000);//guarda el archivo
                    System.out.println("Mensaje del cliente: " + message);

                    //Alternativamente podemos enviar otro mensaje
                    //String response = "Catálogo recibido";
                    //ByteBuffer responseByteBuffer = ByteBuffer.wrap(response.getBytes());
                    //client.write(responseByteBuffer);

                    //Pero en este caso estamos enviando el mismo,
                    // de ahí que necesitemos restaurar las posiciones actuales del byteBuffer utilizado
                    buffer.flip();//es especial porque retornamos la misma información
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