package com.able.studyproject.io.second;

import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @param
 * @author jipeng
 * @date 2020-04-28 19:05
 */
public class ChatServer {
    /**
     *
     */
    private static final Integer DEFAULT_PORT = 8888;
    public static final String QUIT = "quit";

    private ServerSocket serverSocket;

    private Map<Integer, Writer> connectecdClient;


    public void start() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);

            System.out.println("服务器已经启动,正在监听端口:" + DEFAULT_PORT);

            while (true) {
                //等待客户端连接
                Socket socket = serverSocket.accept();
                //@TODO 创建ChatHandler线程 用于连接的客户端事件处理
                new Thread(new ChatHandler(this,socket)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void close(){
        if (serverSocket==null) {
            return;
        }
        try {
            serverSocket.close();
            System.out.println("serverSocket已经关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChatServer() {
        connectecdClient = new ConcurrentHashMap<>();

    }

    public void addClient(Socket socket) throws IOException {
        if (socket == null) {
            return;
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        connectecdClient.put(socket.getPort(), bufferedWriter);
    }

    public void removeClient(Socket socket) throws IOException {
        if (socket == null) {
            return;
        }

        int port = socket.getPort();
        Writer writer = connectecdClient.get(port);
        if (writer != null) {
            writer.close();
            connectecdClient.remove(port);
            System.out.println("客户端【" + port + "】已经断开连接了");
        }
        boardcastMsg(socket,"客户端【" + port + "】已经退出");

    }

    public void boardcastMsg(Socket socket, String msg) {
        if (socket == null || StringUtils.isEmpty(msg)) {
            return;
        }
        int port = socket.getPort();
        connectecdClient.forEach((k, v) -> {
            if (!k.equals(port)) {
                try {
                    v.write("来自" + port + "的消息" + msg);
                    v.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}

