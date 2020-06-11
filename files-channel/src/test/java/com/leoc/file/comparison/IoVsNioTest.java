package com.leoc.file.comparison;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.LongStream;

public class IoVsNioTest {
    private static File file = IoVsNio.createFile(10);

    public IoVsNioTest() throws IOException {
    }

    @BeforeClass
    public static void setup() {
        file = IoVsNio.createFile(25);

    }

    @AfterClass
    public static void tearDown() {
        file.delete();
    }

    @Test
    public void testIo() throws Exception {
        LongStream.Builder results = LongStream.builder();
        results.add(IoVsNio.withIO(file));
        results.add(IoVsNio.withIO(file));
        results.add(IoVsNio.withIO(file));
        results.add(IoVsNio.withIO(file));
        results.add(IoVsNio.withIO(file));
        results.add(IoVsNio.withIO(file));
        System.out.println(results.build().average());
    }

    @Test
    public void testNio() throws Exception {
        LongStream.Builder results = LongStream.builder();
        results.add(IoVsNio.withNio(file));
        results.add(IoVsNio.withNio(file));
        results.add(IoVsNio.withNio(file));
        results.add(IoVsNio.withNio(file));
        results.add(IoVsNio.withNio(file));
        results.add(IoVsNio.withNio(file));
        System.out.println(results.build().average());
    }
}
