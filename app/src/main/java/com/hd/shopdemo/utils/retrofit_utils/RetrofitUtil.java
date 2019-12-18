package com.hd.shopdemo.utils.retrofit_utils;


import com.hd.shopdemo.app.AppConfig;
import com.hd.shopdemo.bean.BaseBean;
import com.hd.shopdemo.bean.DataDemoBean;
import com.hd.shopdemo.ui.home.bean.HomeBottomGoodsItemBean;
import com.hd.shopdemo.ui.home.bean.HomeCenterItemBean;
import com.hd.shopdemo.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static final int DEFAULT_TIMEOUT = 15;
    private Retrofit mRetrofit;
    private ApiServer mApiServer;
    private String TAG = "ApiRetrofit %s";
    private static RetrofitUtil retrofitUtil;


    public RetrofitUtil() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//配置连接超时
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//配置写入超时
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//配置读取超时
                .retryOnConnectionFailure(true);//默认重连一次，若需要重试N次，则要实现拦截器。

        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.APP_SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        mApiServer = mRetrofit.create(ApiServer.class);
    }

    public static RetrofitUtil getInstance() {
        if (retrofitUtil == null) {
            synchronized (Object.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil();
                }
            }
        }
        return retrofitUtil;
    }

    public void getHomeBottomGoodsItemBean(String page, String rows, Callback<BaseBean<HomeBottomGoodsItemBean>> callback) {
        LogUtil.i("首页底部商品   pams：" + page + "," + rows);
        mApiServer.getHomeBottomGoodsItemBean(page, rows).enqueue(callback);
    }

    public void getHomeCenterItemBean(Callback<BaseBean<HomeCenterItemBean>> callback) {
        LogUtil.i("首页内容   pams：");
        mApiServer.getHomeCenterItemBean().enqueue(callback);
    }


    ////下面都是没用的
    public void getBuyproductProductlist(String uid, String longAlt, String pageIndex, String pageSize, String ptid, String cid, Callback<DataDemoBean> callback) {
        mApiServer.getBuyproductProductlist(uid, longAlt, pageIndex, pageSize, ptid, cid).enqueue(callback);
    }


    public void getBuyproductProductlist2(String uid, String longAlt, String pageIndex, String pageSize, String ptid, String cid, Callback<DataDemoBean> callback) {
        // 设置请求类型
        MediaType mediaType = MediaType.parse("application/json;charset=UTF-8");
        // 设置请求内容
        JSONObject jsonObject = new JSONObject();
        try {
            //商品列表par：uid=&longAlt=116.557634,39.788382&pageIndex=0&pageSize=10&ptid=0&cid=36
            jsonObject.put("uid", uid);
            jsonObject.put("longAlt", longAlt);
            jsonObject.put("pageIndex", pageIndex);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("ptid", ptid);
            jsonObject.put("cid", cid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(mediaType, str);
        mApiServer.getBuyproductProductlist2(requestBody).enqueue(callback);
    }


    public void getBuyproductProductlist3(String uid, String longAlt, String pageIndex, String pageSize, String ptid, String cid, Callback<DataDemoBean> callback) {
        // 设置请求内容
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("longAlt", longAlt);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        map.put("ptid", ptid);
        map.put("cid", cid);
        mApiServer.getBuyproductProductlist3(map).enqueue(callback);
    }

}
