package com.lawyus.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {

    /**
     * Server服务端首先创建ServerSocket监听8000端口,然后不断调用阻塞方法 serversocket.accept()获取新的连接,
     * 当获取到新的连接给每条连接创建新的线程负责从该连接中读取数据,然后读取数据是以字节流的方式
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                // 新建线程接收数据
                new Thread(() -> {
                    try {
                        InputStream input = socket.getInputStream();
                        byte[] data = new byte[1024];
                        int length;
                        while ((length = input.read(data)) != -1) {
                            System.out.println(new String(data, 0, length));
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


