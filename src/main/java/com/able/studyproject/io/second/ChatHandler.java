package com.able.studyproject.io.second;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @param
 * @author jipeng
 * @date 2020-04-28 19:05
 */
public class ChatHandler implements Runnable {
    private ChatServer chatServer;
    private Socket socket;

    public ChatHandler(ChatServer chatServer, Socket socket) {
        this.chatServer = chatServer;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //将客户端添加到服务器中
                chatServer.addClient(socket);
                //读取用户发送的消息
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s = bufferedReader.readLine();
                System.out.println("客户端【"+socket.getPort()+"】:"+s);
                //用户退出
                if (!StringUtils.isEmpty(s)&& ChatServer.QUIT.equals(s)) {
                    chatServer.removeClient(socket);
                    socket=null;
                    break;
                }
                //将消息转发给聊天室中的其他在线用户
                chatServer.boardcastMsg(socket,s+"\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket!=null) {
                try {
                    chatServer.removeClient(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

