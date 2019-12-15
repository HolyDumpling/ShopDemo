package com.hd.shopdemo.bean;

import java.util.List;

public class HomeContentBean {
    List<BannerData> bannerDataList;
    List<ClassifyData> classifyDataList;
    String unionTitleImg;
    String unionTitleBgColor;
    List<UnionClassifyData> unionClassifyDataList;
    List<UnionItemData> unionItemDataList;
    String singleItem_1_TitleImg;
    List<SingleItem_1_Data> singleItem_1_DataList;
    String singleItem_2_TitleImg;
    List<SingleItem_2_Data> singleItem_2_DataList;

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

    public List<BannerData> getBannerDataList() {
        return bannerDataList;
    }

    public void setBannerDataList(List<BannerData> bannerDataList) {
        this.bannerDataList = bannerDataList;
    }

    public List<ClassifyData> getClassifyDataList() {
        return classifyDataList;
    }

    public void setClassifyDataList(List<ClassifyData> classifyDataList) {
        this.classifyDataList = classifyDataList;
    }

    public List<UnionClassifyData> getUnionClassifyDataList() {
        return unionClassifyDataList;
    }

    public void setUnionClassifyDataList(List<UnionClassifyData> unionClassifyDataList) {
        this.unionClassifyDataList = unionClassifyDataList;
    }

    public List<UnionItemData> getUnionItemDataList() {
        return unionItemDataList;
    }

    public void setUnionItemDataList(List<UnionItemData> unionItemDataList) {
        this.unionItemDataList = unionItemDataList;
    }

    public List<SingleItem_1_Data> getSingleItem_1_DataList() {
        return singleItem_1_DataList;
    }

    public void setSingleItem_1_DataList(List<SingleItem_1_Data> singleItem_1_DataList) {
        this.singleItem_1_DataList = singleItem_1_DataList;
    }

    public List<SingleItem_2_Data> getSingleItem_2_DataList() {
        return singleItem_2_DataList;
    }

    public void setSingleItem_2_DataList(List<SingleItem_2_Data> singleItem_2_DataList) {
        this.singleItem_2_DataList = singleItem_2_DataList;
    }

    public static class BannerData {
        private String img;
        private String bgColor;
        private int type;
        private int sid;
        private int gid;

        public BannerData(String img, String bgColor, int type, int sid, int gid) {
            this.img = img;
            this.bgColor = bgColor;
            this.type = type;
            this.sid = sid;
            this.gid = gid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getBgColor() {
            return bgColor;
        }

        public void setBgColor(String bgColor) {
            this.bgColor = bgColor;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }

    public static class ClassifyData {
        private String img;
        private String title;
        private int type;
        private int sid;
        private int gid;

        public ClassifyData(String img, String title, int type, int sid, int gid) {
            this.img = img;
            this.title = title;
            this.type = type;
            this.sid = sid;
            this.gid = gid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }

    public static class UnionClassifyData {
        private String img;
        private String title;
        private int type;
        private int sid;
        private int gid;

        public UnionClassifyData(String img, String title, int type, int sid, int gid) {
            this.img = img;
            this.title = title;
            this.type = type;
            this.sid = sid;
            this.gid = gid;
        }

        public UnionClassifyData() {
            this.img = "";
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }

    public static class UnionItemData {
        private String img;
        private String title;
        private int type;
        private int sid;
        private int gid;

        public UnionItemData(String img, String title, int type, int sid, int gid) {
            this.img = img;
            this.title = title;
            this.type = type;
            this.sid = sid;
            this.gid = gid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }

    public static class SingleItem_1_Data {
        private String img;
        private String title;
        private int type;
        private int sid;
        private int gid;

        public SingleItem_1_Data(String img, String title, int type, int sid, int gid) {
            this.img = img;
            this.title = title;
            this.type = type;
            this.sid = sid;
            this.gid = gid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }

    public static class SingleItem_2_Data {
        private String img;
        private String title;
        private int type;
        private int sid;
        private int gid;

        public SingleItem_2_Data(String img, String title, int type, int sid, int gid) {
            this.img = img;
            this.title = title;
            this.type = type;
            this.sid = sid;
            this.gid = gid;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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
    }
}
