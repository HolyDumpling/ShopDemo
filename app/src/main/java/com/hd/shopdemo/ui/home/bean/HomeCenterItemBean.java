package com.hd.shopdemo.ui.home.bean;

import java.util.List;

public class HomeCenterItemBean {


    /**
     * classifyDataList : [{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg","s_id":"4299","s_title":"张亮麻辣烫","type":0},{"g_id":0,"s_img":"http://onecdn.baozhen100.com/seller/201911/f1bdf1b3c3eb4eaba97e7731063a1673.jpg","s_id":"4308","s_title":"明日正一堂","type":0},{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201908/8bb4cf714b4a4e318387b814bdca3330.jpg","s_id":"4309","s_title":"得味炭火牛蛙","type":0}]
     * singleItem_1_dataList : [{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg","s_id":4299,"s_title":"张亮麻辣烫","type":0},{"g_id":0,"s_img":"http://onecdn.baozhen100.com/seller/201911/f1bdf1b3c3eb4eaba97e7731063a1673.jpg","s_id":4308,"s_title":"明日正一堂","type":0},{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201908/8bb4cf714b4a4e318387b814bdca3330.jpg","s_id":4309,"s_title":"得味炭火牛蛙","type":0}]
     * singleItem_1_TitleImg : 139.196.212.61:8080/goods/public/images/single_head/single_head_1.png
     * singleItem_2_dataList : [{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg","s_id":4299,"s_title":"张亮麻辣烫","type":0},{"g_id":0,"s_img":"http://onecdn.baozhen100.com/seller/201911/f1bdf1b3c3eb4eaba97e7731063a1673.jpg","s_id":4308,"s_title":"明日正一堂","type":0},{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201908/8bb4cf714b4a4e318387b814bdca3330.jpg","s_id":4309,"s_title":"得味炭火牛蛙","type":0}]
     * singleItem_2_TitleImg : 139.196.212.61:8080/goods/public/images/single_head/single_head_2.png
     * unionClassifyDataList : [{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg","s_id":4299,"s_title":"张亮麻辣烫","type":0},{"g_id":0,"s_img":"http://onecdn.baozhen100.com/seller/201911/f1bdf1b3c3eb4eaba97e7731063a1673.jpg","s_id":4308,"s_title":"明日正一堂","type":0},{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201908/8bb4cf714b4a4e318387b814bdca3330.jpg","s_id":4309,"s_title":"得味炭火牛蛙","type":0}]
     * unionItemDataList : [{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg","s_id":4299,"s_title":"张亮麻辣烫","type":0},{"g_id":0,"s_img":"http://onecdn.baozhen100.com/seller/201911/f1bdf1b3c3eb4eaba97e7731063a1673.jpg","s_id":4308,"s_title":"明日正一堂","type":0},{"g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201908/8bb4cf714b4a4e318387b814bdca3330.jpg","s_id":4309,"s_title":"得味炭火牛蛙","type":0}]
     * unionTitleBgColor : #53aa76
     * unionTitleImg : 139.196.212.61:8080/goods/public/images/union_head/union_head_1.png
     * bannerDataList : [{"bgColor":"","g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg","s_id":4299,"type":1},{"bgColor":"","g_id":0,"s_img":"http://onecdn.baozhen100.com/seller/201911/f1bdf1b3c3eb4eaba97e7731063a1673.jpg","s_id":4308,"type":1},{"bgColor":"","g_id":0,"s_img":"http://onecdndev.baozhen100.com/seller/201908/8bb4cf714b4a4e318387b814bdca3330.jpg","s_id":4309,"type":1}]
     */

    private String singleItem_1_TitleImg;
    private String singleItem_2_TitleImg;
    private String unionTitleBgColor;
    private String unionTitleImg;
    private List<ClassifyDataListBean> classifyDataList;
    private List<SingleItem1DataListBean> singleItem_1_dataList;
    private List<SingleItem2DataListBean> singleItem_2_dataList;
    private List<UnionClassifyDataListBean> unionClassifyDataList;
    private List<UnionItemDataListBean> unionItemDataList;
    private List<BannerDataListBean> bannerDataList;

    public String getSingleItem_1_TitleImg() {
        return singleItem_1_TitleImg;
    }

    public void setSingleItem_1_TitleImg(String singleItem_1_TitleImg) {
        this.singleItem_1_TitleImg = singleItem_1_TitleImg;
    }

    public String getSingleItem_2_TitleImg() {
        return singleItem_2_TitleImg;
    }

    public void setSingleItem_2_TitleImg(String singleItem_2_TitleImg) {
        this.singleItem_2_TitleImg = singleItem_2_TitleImg;
    }

    public String getUnionTitleBgColor() {
        return unionTitleBgColor;
    }

    public void setUnionTitleBgColor(String unionTitleBgColor) {
        this.unionTitleBgColor = unionTitleBgColor;
    }

    public String getUnionTitleImg() {
        return unionTitleImg;
    }

    public void setUnionTitleImg(String unionTitleImg) {
        this.unionTitleImg = unionTitleImg;
    }

    public List<ClassifyDataListBean> getClassifyDataList() {
        return classifyDataList;
    }

    public void setClassifyDataList(List<ClassifyDataListBean> classifyDataList) {
        this.classifyDataList = classifyDataList;
    }

    public List<SingleItem1DataListBean> getSingleItem_1_dataList() {
        return singleItem_1_dataList;
    }

    public void setSingleItem_1_dataList(List<SingleItem1DataListBean> singleItem_1_dataList) {
        this.singleItem_1_dataList = singleItem_1_dataList;
    }

    public List<SingleItem2DataListBean> getSingleItem_2_dataList() {
        return singleItem_2_dataList;
    }

    public void setSingleItem_2_dataList(List<SingleItem2DataListBean> singleItem_2_dataList) {
        this.singleItem_2_dataList = singleItem_2_dataList;
    }

    public List<UnionClassifyDataListBean> getUnionClassifyDataList() {
        return unionClassifyDataList;
    }

    public void setUnionClassifyDataList(List<UnionClassifyDataListBean> unionClassifyDataList) {
        this.unionClassifyDataList = unionClassifyDataList;
    }

    public List<UnionItemDataListBean> getUnionItemDataList() {
        return unionItemDataList;
    }

    public void setUnionItemDataList(List<UnionItemDataListBean> unionItemDataList) {
        this.unionItemDataList = unionItemDataList;
    }

    public List<BannerDataListBean> getBannerDataList() {
        return bannerDataList;
    }

    public void setBannerDataList(List<BannerDataListBean> bannerDataList) {
        this.bannerDataList = bannerDataList;
    }

    public static class ClassifyDataListBean {
        /**
         * g_id : 0
         * s_img : http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg
         * s_id : 4299
         * s_title : 张亮麻辣烫
         * type : 0
         */

        private int g_id;
        private String s_img;
        private String s_id;
        private String s_title;
        private int type;

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getS_img() {
            return s_img;
        }

        public void setS_img(String s_img) {
            this.s_img = s_img;
        }

        public String getS_id() {
            return s_id;
        }

        public void setS_id(String s_id) {
            this.s_id = s_id;
        }

        public String getS_title() {
            return s_title;
        }

        public void setS_title(String s_title) {
            this.s_title = s_title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class SingleItem1DataListBean {
        /**
         * g_id : 0
         * s_img : http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg
         * s_id : 4299
         * s_title : 张亮麻辣烫
         * type : 0
         */

        private int g_id;
        private String s_img;
        private int s_id;
        private String s_title;
        private int type;

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getS_img() {
            return s_img;
        }

        public void setS_img(String s_img) {
            this.s_img = s_img;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getS_title() {
            return s_title;
        }

        public void setS_title(String s_title) {
            this.s_title = s_title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class SingleItem2DataListBean {
        /**
         * g_id : 0
         * s_img : http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg
         * s_id : 4299
         * s_title : 张亮麻辣烫
         * type : 0
         */

        private int g_id;
        private String s_img;
        private int s_id;
        private String s_title;
        private int type;

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getS_img() {
            return s_img;
        }

        public void setS_img(String s_img) {
            this.s_img = s_img;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getS_title() {
            return s_title;
        }

        public void setS_title(String s_title) {
            this.s_title = s_title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class UnionClassifyDataListBean {
        /**
         * g_id : 0
         * s_img : http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg
         * s_id : 4299
         * s_title : 张亮麻辣烫
         * type : 0
         */

        private int g_id;
        private String s_img;
        private int s_id;
        private String s_title;
        private int type;

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getS_img() {
            return s_img;
        }

        public void setS_img(String s_img) {
            this.s_img = s_img;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getS_title() {
            return s_title;
        }

        public void setS_title(String s_title) {
            this.s_title = s_title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class UnionItemDataListBean {
        /**
         * g_id : 0
         * s_img : http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg
         * s_id : 4299
         * s_title : 张亮麻辣烫
         * type : 0
         */

        private int g_id;
        private String s_img;
        private int s_id;
        private String s_title;
        private int type;

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getS_img() {
            return s_img;
        }

        public void setS_img(String s_img) {
            this.s_img = s_img;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getS_title() {
            return s_title;
        }

        public void setS_title(String s_title) {
            this.s_title = s_title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class BannerDataListBean {
        /**
         * bgColor :
         * g_id : 0
         * s_img : http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg
         * s_id : 4299
         * type : 1
         */

        private String bgColor;
        private int g_id;
        private String s_img;
        private int s_id;
        private int type;

        public String getBgColor() {
            return bgColor;
        }

        public void setBgColor(String bgColor) {
            this.bgColor = bgColor;
        }

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getS_img() {
            return s_img;
        }

        public void setS_img(String s_img) {
            this.s_img = s_img;
        }

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
