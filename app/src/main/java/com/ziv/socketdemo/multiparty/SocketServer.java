package com.ziv.socketdemo.multiparty;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多端登录——服务
 * 服务端主程序监听某一个端口
 * 客户端发起链接请求后，服务端接收的同时构造一个线程类
 * 线程接管会话，主线程继续监听
 * <p>
 * Created by ziv on 17-3-3.
 */

public class SocketServer extends ServerSocket {
    private static final int SERVER_PORT = 2222;

    public SocketServer() throws IOException {
        super(SERVER_PORT);

        Socket socket = accept();
        // 开启线程处理请求
        new CreateServerThread(socket);
        // TODO: ??? close what? ServerSocket?
        close();
    }

    class CreateServerThread extends Thread {
        private Socket client;
        private BufferedReader bufferedReader;
        private PrintWriter printWriter;

        public CreateServerThread(Socket client) throws IOException {
            this.client = client;

            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            printWriter = new PrintWriter(client.getOutputStream(), true);

            Log.e("ziv", "Client " + getName() + " come in ...");

            start();
        }

        @Override
        public void run() {
            try {
                String line = bufferedReader.readLine();
                while (!line.equals("bye")) {
                    printWriter.println("continue, Client " + getName());
                    line = bufferedReader.readLine();
                    Log.e("ziv", "Client " + getName() + " say: " + line);
                }
                printWriter.println("bye, Client " + getName());
                Log.e("ziv", "Client " + getName() + " exit!");

                printWriter.close();
                bufferedReader.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
