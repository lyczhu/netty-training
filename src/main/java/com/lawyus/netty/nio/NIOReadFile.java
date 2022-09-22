package com.lawyus.netty.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOReadFile {

    public static void main(String[] args) {
        channelRead();
    }

    public static void randomRead() {
        try (RandomAccessFile raf = new RandomAccessFile("C:\\文本.txt", "rw")) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = raf.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void channelRead() {
        try (RandomAccessFile raf = new RandomAccessFile("C:\\文本.txt", "rw")) {
            FileChannel fileChannel = raf.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }
                byteBuffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
