package com.hd.shopdemo.ui.game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hd.shopdemo.R;
import com.hd.shopdemo.base.BaseActivity;
import com.hd.shopdemo.utils.websocket.MyWebSocketClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends BaseActivity {
    MyWebSocketClient webSocketClient;
    @BindView(R.id.et_input)
    EditText et_input;
    @BindView(R.id.bt_submit)
    Button bt_submit;
    @BindView(R.id.tv_center)
    TextView tv_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        initPermissions();
        webSocketClient = MyWebSocketClient.getInstance();
        initViews();
        initDatas();
    }

    private void initPermissions() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @OnClick(R.id.bt_submit)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                if (!webSocketClient.isOpen())
                    webSocketClient.connect();
                webSocketClient.send("你好");
                /*
                    try {
                        if(!webSocketClient.isOpen())
                            webSocketClient.connectBlocking();
                        webSocketClient.send("你好");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        LogUtil.i("发送消息失败："+e.getMessage());
                    }*/
                break;
        }
    }

}
