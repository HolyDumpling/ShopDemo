package com.hd.shopdemo.app;

import android.content.Context;
import android.content.Intent;

import com.hd.shopdemo.MainActivity;

/**
 * 跳转界面
 */
public class AppIntent {
    /*** 主界面 **/
    public static Intent getMainActivity(Context context) {
        return new Intent(context, MainActivity.class);
    }
}
