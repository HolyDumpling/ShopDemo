package com.hd.shopdemo.utils.retrofit_utils;


import com.hd.shopdemo.bean.BaseBean;
import com.hd.shopdemo.bean.DataDemoBean;
import com.hd.shopdemo.ui.home.bean.HomeBottomGoodsItemBean;
import com.hd.shopdemo.ui.home.bean.HomeCenterItemBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public interface ApiServer {
    /*****首页商品列表*****/
    String BUYPRODUCT_PRODUCTLIST = "/api/buyProduct_productList";

    /*****首页底部商品列表*****/
    String HOME_BOTTOMGOODSLIST = "home/getItemList";

    /*****首页内容*****/
    String HOME_CENTERDATA = "home/responseHeadData";

    @POST(HOME_BOTTOMGOODSLIST)
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<BaseBean<HomeBottomGoodsItemBean>> getHomeBottomGoodsItemBean(@Field("page") String page, @Field("rows") String rows);

    @POST(HOME_CENTERDATA)
    Call<BaseBean<HomeCenterItemBean>> getHomeCenterItemBean();



    @POST(BUYPRODUCT_PRODUCTLIST)
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<DataDemoBean> getBuyproductProductlist(@Field("uid") String uid, @Field("longAlt") String longAlt, @Field("pageIndex") String pageIndex, @Field("pageSize") String pageSize, @Field("ptid") String ptid, @Field("cid") String cid);

    @POST(BUYPRODUCT_PRODUCTLIST)
    @Headers("Content-Type:application/json")
    Call<DataDemoBean> getBuyproductProductlist2(@Body RequestBody requestBody);

    @POST(BUYPRODUCT_PRODUCTLIST)
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<DataDemoBean> getBuyproductProductlist3(@FieldMap Map<String, String> map);

    @Streaming
    @GET("download")
    Call<ResponseBody> downloadFile();
}