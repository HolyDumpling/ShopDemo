package com.hd.shopdemo.ui.home;

import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.widget.my_brvah.MyMultiItemEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeCenterItem extends MyMultiItemEntity {
    public static final int HOMECENTER_TYPE_COUNT = 13;//分类总数
    public static final int HOMECENTER_HOME_BANNER = 0;//首页轮播
    public static final int HOMECENTER_HOME_CLASSIFY = 1;//首页五分类
    public static final int HOMECENTER_HOME_UNION_TITLE = 2;//首页联盟卡标题
    public static final int HOMECENTER_HOME_UNION_CLASSIFY = 3;//首页联盟卡田字格
    public static final int HOMECENTER_HOME_UNION_ITEM = 4;//首页联盟卡套餐
    public static final int HOMECENTER_HOME_SINGLE_TITLE = 5;//首页单列项(限时爆款、每日店推)的标题
    public static final int HOMECENTER_HOME_SINGLE_ITEM_1 = 6;//首页单列项(限时爆款)
    public static final int HOMECENTER_HOME_SINGLE_ITEM_2 = 7;//首页单列项(每日店推)
    public static final int HOMECENTER_HOME_DOUBLE_TITLE = 8;//首页双列项(底部商品)的标题
    public static final int HOMECENTER_HOME_DOUBLE_ITEM = 9;//首页双列项(底部商品)

    public static final int HOMECENTER_APPENDIX_BANNER = 10;//附页轮播
    public static final int HOMECENTER_APPENDIX_SINGLE_ITEM = 11;//附页单列项
    public static final int HOMECENTER_APPENDIX_EMPTY = 12;//附页缺省页

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
    //序号
    private int position;
    //顶部是否是圆角
    private boolean topRounded;
    //底部是否是圆角
    private boolean bottomRounded;

    public HomeCenterItem(int itemType, int spanSize, String imgUrl, String bgColor, String title, String center) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.imgUrl = imgUrl;
        this.bgColor = bgColor;
        this.title = title;
        this.center = center;
    }

    public HomeCenterItem(int itemType, int spanSize, String imgUrl, String bgColor, String title, String center, int position) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.imgUrl = imgUrl;
        this.bgColor = bgColor;
        this.title = title;
        this.center = center;
        this.position = position;
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

    @Override
    public void setNewItem(MyMultiItemEntity newItem) {
        HomeCenterItem item = (HomeCenterItem) newItem;
        this.bannerList = item.bannerList;
        this.btText_1 = item.getBtText_1();
        this.btText_2 = item.getBtText_2();
        this.subscript = item.getSubscript();
        this.status = item.getStatus();
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
