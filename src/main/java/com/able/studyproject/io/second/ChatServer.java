package com.able.studyproject.io.second;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @author jipeng
 * @date 2020-04-28 19:05
 */
public class ChatServer {
    /**
    *
    */
    private static final Integer DEFAULT_PORT=8888;
    private final String QUIT="quit";

    private  ServerSocket serverSocket;

    private Map<Integer, Writer> connectecdClient;

    public ChatServer() throws IOException {
        serverSocket=new ServerSocket(DEFAULT_PORT);
        connectecdClient=new HashMap<>();
    }

    public void addClient(Socket socket) throws IOException {
        if (socket==null) {
            return;
        }
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        connectecdClient.put(socket.getPort(),bufferedWriter);
    }

}

