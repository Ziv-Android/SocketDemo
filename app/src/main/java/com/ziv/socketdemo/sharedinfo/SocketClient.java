package com.ziv.socketdemo.sharedinfo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 多客户端共享（聊天室）
 * <p>
 * Created by ziv on 17-3-3.
 */

public class SocketClient extends Socket {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 2222;

    private Socket client;
    private PrintWriter out;
    private BufferedReader in;


    /**
     * 与服务器连接，并输入发送消息
     *
     * @param input 被发送消息流
     * @throws IOException
     */
    public SocketClient(InputStream input) throws IOException {
        super(SERVER_IP, SERVER_PORT);

        client = this;
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        new readLineThread();

        while (true) {
            in = new BufferedReader(new InputStreamReader(input));
            String string = in.readLine();
            out.println(string);
        }
    }

    /**
     * 用于监听服务器向客户端发送消息线程类
     */
    private class readLineThread extends Thread {
        private BufferedReader buff;

        public readLineThread() {
            try {
                buff = new BufferedReader(new InputStreamReader(client.getInputStream()));
                start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String line = buff.readLine();

                    if ("byeClient".equals(line)){
                        // 客户端申请退出
                        break;
                    }else {
                        // 输出服务器发出的消息
                        Log.e("ziv", "msg from server: " + line);
                    }
                }
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
