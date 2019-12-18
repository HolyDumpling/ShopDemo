package com.hd.shopdemo.ui.classify;

import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.widget.my_brvah.MyMultiItemEntity;

import java.util.ArrayList;
import java.util.List;

public class ClassifyRightItem extends MyMultiItemEntity {
    public static final int CLASSIFY_RIGHT_TYPE_COUNT = 4;//分类总数
    public static final int CLASSIFY_RIGHT_BANNER = 0;//分类轮播
    public static final int CLASSIFY_RIGHT_TITLE = 1;//分类标题
    public static final int CLASSIFY_RIGHT_AD = 2;//分类广告
    public static final int CLASSIFY_RIGHT_ITEM = 3;//分类项

    private int itemType;
    private int spanSize;
    private int position;
    private int itemCount;
    //轮播图数据
    private List<CustomData> bannerList = new ArrayList<>();

    //标题
    private String title;
    //图片
    private String img;
    //商店ID
    private int sid = -1;
    //商品ID
    private int gid = -1;

    public ClassifyRightItem(int itemType, int spanSize, List<CustomData> bannerList) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.bannerList = bannerList;
    }

    public ClassifyRightItem(int itemType, int spanSize, int sid, String title) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.sid = sid;
        this.title = title;
    }

    public ClassifyRightItem(int itemType, int spanSize, int sid, int gid, String img) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.sid = sid;
        this.gid = gid;
        this.img = img;
    }

    public ClassifyRightItem(int itemType, int spanSize, int gid, String title, String img, int position, int itemCount) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.gid = gid;
        this.title = title;
        this.img = img;
        this.position = position;
        this.itemCount = itemCount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<CustomData> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<CustomData> bannerList) {
        this.bannerList = bannerList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
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

    @Override
    public void setNewItem(MyMultiItemEntity newItem) {
        ClassifyRightItem item = (ClassifyRightItem) newItem;
        this.itemType = item.getItemType();
        this.spanSize = item.getSpanSize();
        this.sid = item.getSid();
        this.gid = item.getGid();
        this.title = item.getTitle();
        this.img = item.getImg();
        this.position = item.getPosition();
        this.itemCount = item.getItemCount();
    }
}
