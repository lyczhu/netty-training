package com.lawyus.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread {

    class Server implements Runnable {
        @Override
        public void run() {
            try {
                ServerSocket ss = new ServerSocket(8000);
                while (!Thread.interrupted()) {
                    // 重点是演示这里的socket连接处理 多线程调用方式
                    new Thread(new Handler(ss.accept())).start();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Handler implements Runnable {
        final Socket socket;
        Handler(Socket s) {
            socket = s;
        }

        @Override
        public void run() {
            byte[] input = new byte[1024];
            try {
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        byte[] process(byte[] input) {
            return input;
        }
    }
}



