package com.able.studyproject.io.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @param
 * @author jipeng
 * @date 2020-04-28 19:05
 */
public class UserInpultHandler implements Runnable{

    private ChatClient chatClient;

    public UserInpultHandler(ChatClient chatClient){
        this.chatClient=chatClient;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String s = bufferedReader.readLine();

                //向服务器发送消息
                chatClient.send(s);
                //是否要退出
                if (chatClient.readyToQuite(s)) {
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}

