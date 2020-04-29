package com.able.studyproject.io.second;

import java.io.*;
import java.net.Socket;

/**
 * @param
 * @author jipeng
 * @date 2020-04-28 19:05
 */
public class ChatClient {

    public static final String QUIT = "quit";
    /**
    *
    */
    public static final Integer DEFAULT_SERVER_PORT=8888;
    public static final String DEFAULT_HOST="127.0.0.1";

    Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    public static void main(String[] args){
      ChatClient chatClient=new ChatClient();
      chatClient.start();
    }

    public void start() {
        try {
            socket=new Socket(DEFAULT_HOST,DEFAULT_SERVER_PORT);
            System.out.println("欢迎上线：【客户端】");
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //处理用户的输入
            new Thread(new UserInpultHandler(this)).start();

            //读取服务器转发的消息

            receive();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }

    }
    public void close(){
        try {
            System.out.println("关闭客户端的资源");
            if (socket!=null){
                socket.close();
            }
            if (bufferedWriter!=null){
                bufferedWriter.close();
            }
            if (bufferedReader!=null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) throws IOException {
        if (socket!=null&&!socket.isOutputShutdown()) {
            bufferedWriter.write(msg+"\n");
            bufferedWriter.flush();
        }
    }

    public void receive() throws IOException {
        String msg=null;
        while (true) {
            if (socket!=null&&!socket.isInputShutdown()) {

                msg=bufferedReader.readLine();
                if (msg==null) {
                    break;
                }
                System.out.println(msg);
            }

        }
    }
    public boolean readyToQuite(String msg){
       return  QUIT.equals(msg);
    }

}

