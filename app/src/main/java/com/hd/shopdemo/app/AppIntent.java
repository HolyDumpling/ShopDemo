package com.hd.shopdemo.app;

import android.content.Context;
import android.content.Intent;

import com.hd.shopdemo.MainActivity;
import com.hd.shopdemo.ui.game.GameActivity;

/**
 * 跳转界面
 */
public class AppIntent {
    /*** 主界面 **/
    public static Intent getMainActivity(Context context) {
        return new Intent(context, MainActivity.class);
    }

    /*** 游戏界面 **/
    public static Intent getGameActivity(Context context) {
        return new Intent(context, GameActivity.class);
    }
}
