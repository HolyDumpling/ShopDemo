package com.hd.shopdemo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hd.shopdemo.R;
import com.hd.shopdemo.base.BaseFragment;
import com.hd.shopdemo.widget.mybanner.Banner_ImitateJDClassify;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 购物车
 */
public class CartFragment extends BaseFragment {

    @BindView(R.id.myBanner)
    Banner_ImitateJDClassify myBanner;

    String[] banners = {
            "http://139.196.212.61:8080/ShopDemo_war/goods/g_img/dc428073463445489e1332aaff7e983d.jpg",
            "http://onecdndev.baozhen100.com/seller/201912/5b35bbb84d3542d3bb56ab6002dbfe15.jpg",
            "http://onecdndev.baozhen100.com/seller/201911/f8b0e27fd4f54907beb565870edc4d53.jpg",
            "http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg",
            "http://onecdndev.baozhen100.com/seller/201911/81f1f7e835b64f2cb7e9dbbb80504821.png",
            "http://onecdndev.baozhen100.com/seller/201911/0b454b2ee1cb40d890c6107edbf0814a.png",
            "http://onecdndev.baozhen100.com/seller/201911/11b515f494d94167916591ee9e955624.png"
    };

    /*
        String[] banners = {
                "http://onecdndev.baozhen100.com/seller/201912/5b35bbb84d3542d3bb56ab6002dbfe15.jpg",
                "http://onecdndev.baozhen100.com/seller/201911/f8b0e27fd4f54907beb565870edc4d53.jpg",
                "http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg",
                "http://onecdndev.baozhen100.com/seller/201911/81f1f7e835b64f2cb7e9dbbb80504821.png",
                "http://onecdndev.baozhen100.com/seller/201911/0b454b2ee1cb40d890c6107edbf0814a.png",
                "http://onecdndev.baozhen100.com/seller/201911/11b515f494d94167916591ee9e955624.png"
        };
        */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initDatas();
    }

    @Override
    protected void initViews() {
        ArrayList<String> imageUrlList = new ArrayList<>(Arrays.asList(banners));
        myBanner.setImageUrlList(imageUrlList);
    }

    @Override
    protected void initDatas() {
    }

}
