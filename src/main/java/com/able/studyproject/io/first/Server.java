package com.able.studyproject.io.first;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @param
 * @author jipeng
 * @date 2020-04-27 19:36
 */
public class Server {
    public static final String QUITE = "quite";

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("服务器启动,监听端口:8888");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端【" + socket.getPort() + "】 已经连接");
                doSomething(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                System.out.println("serverSocket关闭");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void doSomething(Socket socket) throws IOException {
        //输入流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //输出流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //读取客户端发送的消息
        String msg;
        while ((msg = bufferedReader.readLine()) != null) {

            System.out.println("客户端【" + socket.getPort() + "】:" + msg);
            bufferedWriter.write("服务端响应:" + msg + "\n");
            bufferedWriter.flush();

            if (QUITE.equals(msg)) {
                System.out.println("客户端【"+socket.getPort()+"】要退出啦");
                break;
            }

        }


    }
}

