package com.hd.shopdemo.utils.websocket;

import com.hd.shopdemo.app.AppConfig;
import com.hd.shopdemo.utils.LogUtil;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class MyWebSocketClient extends WebSocketClient {
    private static MyWebSocketClient myWebSocketClient;

    public static MyWebSocketClient getInstance() {
        synchronized (MyWebSocketClient.class) {
            if (myWebSocketClient == null) {
                synchronized (MyWebSocketClient.class) {
                    if (myWebSocketClient == null) {
                        return myWebSocketClient = new MyWebSocketClient(AppConfig.websocketUIR);
                    } else
                        return myWebSocketClient;
                }
            } else
                return myWebSocketClient;
        }
    }

    private MyWebSocketClient(URI serverUri) {
        super(serverUri, new Draft_6455());
        LogUtil.i("JWebSocketClient 创建()");
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        //连接开启时调用
        LogUtil.i("JWebSocketClient onOpen()");
    }

    @Override
    public void onMessage(String message) {
        //连接收到消息时调用
        LogUtil.i("JWebSocketClient onMessage():" + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        //连接关闭时调用
        LogUtil.i("JWebSocketClient onClose()，code：" + code + "，reason：" + reason + "，remote：" + remote);
    }

    @Override
    public void onError(Exception ex) {
        //连接错误时调用
        LogUtil.i("JWebSocketClient onError()：" + ex.getMessage());
    }
}