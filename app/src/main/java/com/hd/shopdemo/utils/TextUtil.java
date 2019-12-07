package com.hd.shopdemo.utils;

import android.annotation.SuppressLint;

@SuppressLint("SimpleDateFormat")
public class TextUtil {
    /**
     * 检查是否为空
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return value == null || "".equals(value) || value.length() == 0;
    }
}

