package com.leoc.file.comparison;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

public class IoVsNio {

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\leoca\\AppData\\Local\\Temp\\io-vs-nio5577439411617059014.data");
        createFile(25);
        System.out.println(file.getAbsolutePath());
        //  withNio(file);
        withIO(file);
        // withNio(file);


    }

    public static File createFile(final long sizeInMB) {
        File file = null;
        try {
            file = File.createTempFile("io-vs-nio", ".data");
            file.createNewFile();

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.setLength(sizeInMB * 100000000);
            raf.close();

            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long withIO(File origin) throws Exception {
        File destination = new File(origin.getAbsolutePath() + ".io-" + UUID.randomUUID());
        long time1 = System.currentTimeMillis();
        InputStream is = new FileInputStream(origin);
        FileOutputStream fos = new FileOutputStream(destination);
        byte[] buf = new byte[64 * 1024];
        int len = 0;
        while ((len = is.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.flush();
        fos.close();
        is.close();
        long time2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (time2 - time1) + " ms with IO");
        destination.delete();
        return time2 - time1;

    }

    public static long withNio(File origin) throws Exception {
        File destination = new File(origin.getAbsolutePath() + ".nio" + UUID.randomUUID());
        long time1 = System.currentTimeMillis();
        FileInputStream is = new FileInputStream(origin);
        FileOutputStream fos = new FileOutputStream(destination);
        FileChannel f = is.getChannel();
        FileChannel f2 = fos.getChannel();
        ByteBuffer buf = ByteBuffer.allocateDirect(64 * 1024);
        long len = 0;
        while ((len = f.read(buf)) != -1) {
            buf.flip();
            f2.write(buf);
            buf.clear();
        }
        f2.close();
        f.close();
        long time2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (time2 - time1) + " ms with NIO");
        destination.delete();
        return time2 - time1;
    }

}
