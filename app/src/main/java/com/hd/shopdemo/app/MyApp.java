package com.hd.shopdemo.app;

import android.app.Application;
import android.content.Context;

import com.hd.shopdemo.utils.websocket.MyWebSocketClient;

public class MyApp extends Application {
    public static Context application;
    public static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        // 引用
        application = this;
        instance = this;

        MyWebSocketClient.getInstance().connect();

    }

    private void initData() {
    }


}
