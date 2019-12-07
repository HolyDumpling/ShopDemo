package com.hd.shopdemo.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by HY
 * on 2018/11/1.
 */
public class LogUtil {
    public static String tagPrefix = "--------";
    public static boolean showV = true;
    public static boolean showD = true;
    public static boolean showI = true;
    public static boolean showW = true;
    public static boolean showE = true;
    public static boolean showWTF = true;
    private static int LOG_MAXLENGTH = 2000;                    // 规定每段显示的长度

    /**
     * 得到tag（所在类.方法（L:行））
     *
     * @return
     */
    private static String generateTag() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        String callerClazzName = stackTraceElement.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        String tag = "%s.%s(L:%d)";
        tag = String.format(tag, callerClazzName, stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()));
        //给tag设置前缀
        tag = TextUtils.isEmpty(tagPrefix) ? tag : tagPrefix + ":" + tag;
        return tag;
    }

    public static void v(String msg) {
        if (showV) {
            String tag = generateTag();
            Log.v(tag, msg);
        }
    }

    public static void v(String msg, Throwable tr) {
        if (showV) {
            String tag = generateTag();
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String msg) {
        if (showD) {
            String tag = generateTag();
            Log.d(tag, msg);
        }
    }

    public static void d(String msg, Throwable tr) {
        if (showD) {
            String tag = generateTag();
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String msg) {
        if (showI) {
            String tag = generateTag();
            int strLength = msg.length();
            int start = 0;
            int end = LOG_MAXLENGTH;
            for (int i = 0; i < 100; i++) {
                //剩下的文本还是大于规定长度则继续重复截取并输出
                if (strLength > end) {
                    Log.i(tag, msg.substring(start, end));
                    start = end;
                    end = end + LOG_MAXLENGTH;
                } else {
                    Log.i(tag, msg.substring(start, strLength));
                    break;
                }
            }
        }
    }

    public static void i(String msg, Throwable tr) {
        if (showI) {
            String tag = generateTag();
            Log.i(tag, msg, tr);
        }
    }

    public static void w(String msg) {
        if (showW) {
            String tag = generateTag();
            Log.w(tag, msg);
        }
    }

    public static void w(String msg, Throwable tr) {
        if (showW) {
            String tag = generateTag();
            Log.w(tag, msg, tr);
        }
    }

    public static void e(String msg) {
        if (showE) {
            String tag = generateTag();
            Log.e(tag, msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (showE) {
            String tag = generateTag();
            Log.e(tag, msg, tr);
        }
    }

    public static void wtf(String msg) {
        if (showWTF) {
            String tag = generateTag();
            Log.wtf(tag, msg);
        }
    }

    public static void wtf(String msg, Throwable tr) {
        if (showWTF) {
            String tag = generateTag();
            Log.wtf(tag, msg, tr);
        }
    }
}
