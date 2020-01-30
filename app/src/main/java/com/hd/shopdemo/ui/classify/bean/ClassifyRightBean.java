package com.hd.shopdemo.ui.classify.bean;

import java.util.List;

public class ClassifyRightBean {
    private List<BannerDataListBean> bannerDataList;
    private List<SubClassifys> itemDataList;

    public static class SubClassifys {
        private String class_title;
        private List<SubClassifyItem> itemList;

        public static class SubClassifyItem {

            /**
             * g_img : http://139.196.212.61:8080/ShopDemo_war/goods/g_img/a6059bb8460942e0b9b06e2a273c0b86.jpg
             * g_title : 贺奇儿童成长乐园套票
             * type : 0
             */

            private String g_img;
            private String g_title;
            private int type;

            public String getG_img() {
                return g_img;
            }

            public void setG_img(String g_img) {
                this.g_img = g_img;
            }

            public String getG_title() {
                return g_title;
            }

            public void setG_title(String g_title) {
                this.g_title = g_title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public String getClass_title() {
            return class_title;
        }

        public void setClass_title(String class_title) {
            this.class_title = class_title;
        }

        public List<SubClassifyItem> getItemList() {
            return itemList;
        }

        public void setItemList(List<SubClassifyItem> itemList) {
            this.itemList = itemList;
        }
    }

    public List<SubClassifys> getItemDataList() {
        return itemDataList;
    }

    public void setItemDataList(List<SubClassifys> itemDataList) {
        this.itemDataList = itemDataList;
    }

    public List<BannerDataListBean> getBannerDataList() {
        return bannerDataList;
    }

    public void setBannerDataList(List<BannerDataListBean> bannerDataList) {
        this.bannerDataList = bannerDataList;
    }

    public static class BannerDataListBean {
        /**
         * bgColor : #8c5bb3
         * g_id : 0
         * s_img : http://139.196.212.61:8080/ShopDemo_war/stores/d7dbff4725624af3ab0d773bdc14cbd2.jpg
         * s_id : 4489
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
