package com.hd.shopdemo.app;

/**
 * 配置文件
 */
public class AppConfig {
    // 生产服务器    https://he.keduoduo100.com
    // 测试服务器    https://bz100.snmun.com
    public final static String APP_SERVER_ADDRESS = "http://139.196.212.61:8080";
    //public static URI websocketUIR = URI.create("ws://echo.websocket.org");
    //全部页面中，标题颜色以及系统状态栏颜色
    public static String statusBgColor = "";
    public static String statusTextColor = "";

    //首页顶部半圆颜色，以及首页标题颜色
    public static String topColor = "#506bde";


    /**
     * 以下为预留属性，暂时无用
     */

    public static String titleColor = "";
    public static String tarBarBgColor = "";
    public static String homeSelected_URL = "";
    public static String homeUnselected_URL = "";
}
