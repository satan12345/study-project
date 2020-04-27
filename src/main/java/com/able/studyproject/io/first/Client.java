package com.able.studyproject.io.first;

import java.io.*;
import java.net.Socket;

/**
 * @param
 * @author jipeng
 * @date 2020-04-27 20:58
 */
public class Client {
    public static final String QUITE = "quite";

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("127.0.0.1", 8888);
            //获得输入流
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获得输出流
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //等待用户输入
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String s = consoleReader.readLine();

                //将用户的控制台输入发送到服务器
                bufferedWriter.write(s + "\n");
                bufferedWriter.flush();
                //读取服务器返回的消息
                String s1 = bufferedReader.readLine();
                System.out.println(s1);
                if (QUITE.equals(s)) {
                    break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                    System.out.println("socket关闭");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

