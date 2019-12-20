package com.hd.shopdemo.ui.home;

import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.ui.home.bean.HomeBottomGoodsItemBean;
import com.hd.shopdemo.ui.home.bean.HomeCenterItemBean;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.widget.my_brvah.MyMultiItemEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeCenterItem extends MyMultiItemEntity {
    public static final int HOMECENTER_TYPE_COUNT = 11;//分类总数
    public static final int HOMECENTER_HOME_TOP_PLACEHOLDER = 0;//首页顶部占位
    public static final int HOMECENTER_HOME_BANNER = 1;//首页轮播
    public static final int HOMECENTER_HOME_CLASSIFY = 2;//首页五分类
    public static final int HOMECENTER_HOME_UNION_TITLE = 3;//首页联盟卡标题
    public static final int HOMECENTER_HOME_UNION_CLASSIFY = 4;//首页联盟卡田字格
    public static final int HOMECENTER_HOME_UNION_ITEM = 5;//首页联盟卡套餐
    public static final int HOMECENTER_HOME_SINGLE_TITLE = 6;//首页单列项(限时爆款、每日店推)的标题
    public static final int HOMECENTER_HOME_SINGLE_ITEM_1 = 7;//首页单列项(限时爆款)
    public static final int HOMECENTER_HOME_SINGLE_ITEM_2 = 8;//首页单列项(每日店推)
    public static final int HOMECENTER_HOME_DOUBLE_TITLE = 9;//首页双列项(底部商品)的标题
    public static final int HOMECENTER_HOME_DOUBLE_ITEM = 10;//首页双列项(底部商品)


    private int itemType;
    private int spanSize;

    //轮播图数据(首页和附加页共用)
    private List<CustomData> bannerList = new ArrayList<>();
    //图片链接
    private String imgUrl;
    //背景色
    private String bgColor;
    //标题
    private String title;
    //状态
    private int status;
    //内容
    private String center;
    //下标
    private String subscript;
    //按钮1
    private String btText_1;
    //按钮2
    private String btText_2;

    private String data_1;
    private String data_2;

    //序号
    private int position;
    //高度
    private int height;
    //顶部是否是圆角
    private boolean topRounded;
    //底部是否是圆角
    private boolean bottomRounded;


    public HomeCenterItem(int itemType, int placeholderHeight) {
        if (itemType == HOMECENTER_HOME_TOP_PLACEHOLDER) {
            this.itemType = itemType;
            this.spanSize = 10;
            this.height = placeholderHeight;
            LogUtil.i("顶部占位高度：" + placeholderHeight);
        }
    }

    public HomeCenterItem(int itemType, int spanSize, List<CustomData> bannerList) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.bannerList = bannerList;
        this.bgColor = "";
    }

    public HomeCenterItem(int itemType, HomeCenterItemBean.SingleItem1DataListBean singleItem_1_data) {
        this.itemType = itemType;
        this.spanSize = 10;
        this.imgUrl = singleItem_1_data.getS_img();
        this.title = singleItem_1_data.getS_title();
        this.bgColor = "";
    }

    public HomeCenterItem(int itemType, HomeCenterItemBean.SingleItem2DataListBean singleItem_2_data) {
        this.itemType = itemType;
        this.spanSize = 10;
        this.imgUrl = singleItem_2_data.getS_img();
        this.title = singleItem_2_data.getS_title();
        this.bgColor = "";
    }

    public HomeCenterItem(int itemType, int spanSize, String imgUrl, String title) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.imgUrl = imgUrl;
        this.title = title;
        this.bgColor = "";
    }

    public HomeCenterItem(int itemType, int spanSize, int position, HomeBottomGoodsItemBean.ItemsBean goods) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.position = position;
        this.imgUrl = goods.getG_img();
        this.title = goods.getG_title();
        this.center = "" + goods.getG_price();
        this.btText_1 = "" + goods.getG_sharePrice();
        this.bgColor = "";
    }

    public HomeCenterItem(int itemType, int spanSize, String imgUrl, String title, String bgColor) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.imgUrl = imgUrl;
        this.title = title;
        this.bgColor = bgColor;
    }

    public HomeCenterItem(int itemType, int spanSize, int position, HomeCenterItemBean.UnionItemDataListBean unionItemData, boolean topRounded, String bgColor) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.position = position;
        this.imgUrl = unionItemData.getS_img();
        this.topRounded = topRounded;
        this.bgColor = bgColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public HomeCenterItem(int itemType, int spanSize, int position, HomeCenterItemBean.UnionClassifyDataListBean unionClassifyData, String bgColor, boolean topRounded, boolean bottomRounded) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.position = position;
        this.imgUrl = unionClassifyData.getS_img();
        this.topRounded = topRounded;
        this.bottomRounded = bottomRounded;
        this.bgColor = bgColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<CustomData> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<CustomData> bannerList) {
        this.bannerList = bannerList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSubscript() {
        return subscript;
    }

    public void setSubscript(String subscript) {
        this.subscript = subscript;
    }

    public String getBtText_1() {
        return btText_1;
    }

    public void setBtText_1(String btText_1) {
        this.btText_1 = btText_1;
    }

    public String getBtText_2() {
        return btText_2;
    }

    public void setBtText_2(String btText_2) {
        this.btText_2 = btText_2;
    }

    public boolean isTopRounded() {
        return topRounded;
    }

    public void setTopRounded(boolean topRounded) {
        this.topRounded = topRounded;
    }

    public boolean isBottomRounded() {
        return bottomRounded;
    }

    public void setBottomRounded(boolean bottomRounded) {
        this.bottomRounded = bottomRounded;
    }

    public String getData_1() {
        return data_1;
    }

    public void setData_1(String data_1) {
        this.data_1 = data_1;
    }

    public String getData_2() {
        return data_2;
    }

    public void setData_2(String data_2) {
        this.data_2 = data_2;
    }

    @Override
    public void setNewItem(MyMultiItemEntity newItem) {
        HomeCenterItem item = (HomeCenterItem) newItem;
        this.bannerList = item.bannerList;
        this.btText_1 = item.getBtText_1();
        this.btText_2 = item.getBtText_2();
        this.subscript = item.getSubscript();
        this.status = item.getStatus();
        this.height = item.getHeight();
        this.itemType = item.getItemType();
        this.spanSize = item.getSpanSize();
        this.imgUrl = item.getImgUrl();
        this.bgColor = item.getBgColor();
        this.center = item.getCenter();
        this.title = item.getTitle();
        this.position = item.getPosition();
        this.topRounded = item.isTopRounded();
        this.bottomRounded = item.isBottomRounded();
    }
}
