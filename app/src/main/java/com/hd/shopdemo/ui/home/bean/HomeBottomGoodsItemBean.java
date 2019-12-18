package com.hd.shopdemo.ui.home.bean;

import java.util.List;

public class HomeBottomGoodsItemBean {
    /**
     * page : 1
     * itemCount : 2
     * items : [{"g_id":0,"g_oldPrice":6799,"g_selfPrice":0,"g_price":1,"g_sharePrice":0,"g_title":"Dell/戴尔灵越7590设计师九代i5/i7超薄15.6英寸窄边框","g_img":"http://onecdndev.baozhen100.com/buyProduct/201909/b0d8eb1983874de18010f4a5b619fcff.png","g_subtitle":"Dell/戴尔灵越7590设计师九代i5/i7超薄15.6英寸窄边框轻薄便携商务办公GTX1050游戏本GTX1650笔记本电脑7000","g_status":"1","g_video":"http://onecdndev.baozhen100.com/buyProduct/201909/7baaf4fc81e844be8d13ef1d5144ee36.mp4","g_videoImg":"http://onecdndev.baozhen100.com/buyProduct/201909/646c89c4dec249e782b3eb9094228030.png","g_salesCount":"778","g_inventoryNum":"185","g_maxSales":"3"},{"g_id":0,"g_oldPrice":255,"g_selfPrice":0,"g_price":0,"g_sharePrice":0,"g_title":"商品时间测试","g_img":"http://onecdndev.baozhen100.com/buyProduct/201911/7b2e0d4c22eb47b6969e939fc9da3396.jpg","g_subtitle":"1","g_status":"1","g_video":"","g_videoImg":"","g_salesCount":"448","g_inventoryNum":"56","g_maxSales":"2"}]
     */

    private int page;
    private int start;
    private int code;
    private int itemCount;
    private String itemHeadImg;
    private List<ItemsBean> itemList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemHeadImg() {
        return itemHeadImg;
    }

    public void setItemHeadImg(String itemHeadImg) {
        this.itemHeadImg = itemHeadImg;
    }

    public List<ItemsBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemsBean> itemList) {
        this.itemList = itemList;
    }

    public static class ItemsBean {
        /**
         * g_id : 0
         * g_oldPrice : 6799
         * g_selfPrice : 0
         * g_price : 1
         * g_sharePrice : 0
         * g_title : Dell/戴尔灵越7590设计师九代i5/i7超薄15.6英寸窄边框
         * g_img : http://onecdndev.baozhen100.com/buyProduct/201909/b0d8eb1983874de18010f4a5b619fcff.png
         * g_subtitle : Dell/戴尔灵越7590设计师九代i5/i7超薄15.6英寸窄边框轻薄便携商务办公GTX1050游戏本GTX1650笔记本电脑7000
         * g_status : 1
         * g_video : http://onecdndev.baozhen100.com/buyProduct/201909/7baaf4fc81e844be8d13ef1d5144ee36.mp4
         * g_videoImg : http://onecdndev.baozhen100.com/buyProduct/201909/646c89c4dec249e782b3eb9094228030.png
         * g_salesCount : 778
         * g_inventoryNum : 185
         * g_maxSales : 3
         */

        private int g_id;
        private int g_oldPrice;
        private int g_selfPrice;
        private int g_price;
        private int g_sharePrice;
        private String g_title;
        private String g_img;
        private String g_subtitle;
        private String g_status;
        private String g_video;
        private String g_videoImg;
        private String g_salesCount;
        private String g_inventoryNum;
        private String g_maxSales;

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public int getG_oldPrice() {
            return g_oldPrice;
        }

        public void setG_oldPrice(int g_oldPrice) {
            this.g_oldPrice = g_oldPrice;
        }

        public int getG_selfPrice() {
            return g_selfPrice;
        }

        public void setG_selfPrice(int g_selfPrice) {
            this.g_selfPrice = g_selfPrice;
        }

        public int getG_price() {
            return g_price;
        }

        public void setG_price(int g_price) {
            this.g_price = g_price;
        }

        public int getG_sharePrice() {
            return g_sharePrice;
        }

        public void setG_sharePrice(int g_sharePrice) {
            this.g_sharePrice = g_sharePrice;
        }

        public String getG_title() {
            return g_title;
        }

        public void setG_title(String g_title) {
            this.g_title = g_title;
        }

        public String getG_img() {
            return g_img;
        }

        public void setG_img(String g_img) {
            this.g_img = g_img;
        }

        public String getG_subtitle() {
            return g_subtitle;
        }

        public void setG_subtitle(String g_subtitle) {
            this.g_subtitle = g_subtitle;
        }

        public String getG_status() {
            return g_status;
        }

        public void setG_status(String g_status) {
            this.g_status = g_status;
        }

        public String getG_video() {
            return g_video;
        }

        public void setG_video(String g_video) {
            this.g_video = g_video;
        }

        public String getG_videoImg() {
            return g_videoImg;
        }

        public void setG_videoImg(String g_videoImg) {
            this.g_videoImg = g_videoImg;
        }

        public String getG_salesCount() {
            return g_salesCount;
        }

        public void setG_salesCount(String g_salesCount) {
            this.g_salesCount = g_salesCount;
        }

        public String getG_inventoryNum() {
            return g_inventoryNum;
        }

        public void setG_inventoryNum(String g_inventoryNum) {
            this.g_inventoryNum = g_inventoryNum;
        }

        public String getG_maxSales() {
            return g_maxSales;
        }

        public void setG_maxSales(String g_maxSales) {
            this.g_maxSales = g_maxSales;
        }
    }
}
