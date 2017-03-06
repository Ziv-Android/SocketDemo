package com.ziv.socketdemo.multiparty;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 多端登录——客户端
 * 1 客户端和服务器建立连接
 * 2 客户端发送消息
 * 3 消息处理
 * 3.1 服务端根据消息进行处理并返回消息
 * 3.2 客户端申请关闭，则服务器关闭此链接，双方通讯结束
 * <p>
 * Created by ziv on 17-3-3.
 */

public class SocketClient {
    public static void login(InputStream input) throws IOException {
        Socket socket = new Socket("172.0.0.1", 2222);
        // 60s超时
        socket.setSoTimeout(60000);

        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String result = "";
        while (result.indexOf("bye") == -1) {
            BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(input));
            // 注意输入数据一定需要包含换行符，不然程序会在readLine方法等待一行的结束，表现状态为假卡死
            printWriter.println(inputBuffer.readLine());
            printWriter.flush();

            result = bufferedReader.readLine();
            Log.e("ziv", "---> Server say: " + result);
        }

        printWriter.close();
        bufferedReader.close();
        socket.close();
    }
}
