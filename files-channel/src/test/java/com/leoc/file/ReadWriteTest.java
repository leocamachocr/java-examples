package com.leoc.file;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ReadWriteTest {


    @Test
    public void readFromFile()
            throws IOException {
        URL fileToRead = Thread.currentThread().getContextClassLoader().getResource("demo-read.txt");
        try (RandomAccessFile reader = new RandomAccessFile(fileToRead.getFile(), "r");
             FileChannel channel = reader.getChannel();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
            while (channel.read(buff) > 0) {
                out.write(buff.array(), 0, buff.position());
                buff.clear();
            }
            String fileContent = new String(out.toByteArray(), StandardCharsets.UTF_8);
            assertEquals("This is a Demo", fileContent);
        }
    }

    @Test
    public void writeToFile()
            throws IOException {
        URL fileToWrite = Thread.currentThread().getContextClassLoader().getResource("demo-write.txt");
        try (RandomAccessFile writer = new RandomAccessFile(fileToWrite.getFile(), "rw");
             FileChannel channel = writer.getChannel()) {
            ByteBuffer buff = ByteBuffer.wrap("Hello world".getBytes(StandardCharsets.UTF_8));

            channel.write(buff);

            // verify
            RandomAccessFile reader = new RandomAccessFile(fileToWrite.getFile(), "r");
            assertEquals("Hello world", reader.readLine());
            reader.close();
        }
    }
}
