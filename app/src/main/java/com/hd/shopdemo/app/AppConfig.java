package com.hd.shopdemo.app;

import java.net.URI;

/**
 * 配置文件
 */
public class AppConfig {
    public static URI websocketUIR = URI.create("http://192.168.1.7:3000");
    //public static URI websocketUIR = URI.create("ws://echo.websocket.org");
    //全部页面中，标题颜色以及系统状态栏颜色
    public static String statusBgColor = "";
    public static String statusTextColor = "";


    /**
     * 以下为预留属性，暂时无用
     */

    //首页顶部半圆颜色，以及首页标题颜色
    public static String topColor = "";
    public static String ip = "http://192.168.1.109:3000";
    public static String titleColor = "";
    public static String tarBarBgColor = "";
    public static String homeSelected_URL = "";
    public static String homeUnselected_URL = "";
}
