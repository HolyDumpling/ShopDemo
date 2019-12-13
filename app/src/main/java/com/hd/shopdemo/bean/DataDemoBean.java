package com.hd.shopdemo.bean;

import java.util.List;

public class DataDemoBean {
    /**
     * data : {"headImg":"http://onecdndev.baozhen100.com/type/201911/f6b532af019648389db4170a3893f7cd.png","totalCount":11,"items":[{"oldPrice":"499.00","sid":4337,"title":"Gucci 古驰罪爱原罪女士淡香水50ml 柔和辛辣花香","selfPrice":"购物返￥0","price":"499.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/bbd3da6db9844e70a0c1faca102b54af.png","isMember":0,"pid":14954,"isShow":2,"sale":"13人付款"},{"oldPrice":"428.00","sid":4337,"title":"Gucci 古驰绚丽栀子花女士淡香水30／50／100ml花香调持久留香礼物","selfPrice":"购物返￥0","price":"428.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/45c4de80c2034518b8754c0552c5b255.png","isMember":0,"pid":14953,"isShow":2,"sale":"1人付款"},{"oldPrice":"2899.00","sid":4337,"title":"Dyson戴森V8 Absolute无线吸尘器顶配6吸头（2主／4配件吸头）美版","selfPrice":"购物返￥0","price":"2899.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/e92204cd5aec48aca16a7a3f7d12b6e7.png","isMember":0,"pid":14952,"isShow":2,"sale":"91人付款"},{"oldPrice":"1289.00","sid":4337,"title":"Danielwellington 丹尼尔惠灵顿dw手表女32mm编织钢带女表","selfPrice":"购物返￥0","price":"1289.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/550cab82c38d40c78a60894d707c1029.png","isMember":0,"pid":14951,"isShow":2,"sale":"3人付款"},{"oldPrice":"258.00","sid":4337,"title":"Dior／迪奥999哑光口红香水套装888 520 礼盒优惠套装","selfPrice":"购物返￥0","price":"258.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/cc62550606c14fab84ebbaf78293b335.png","isMember":0,"pid":14950,"isShow":2,"sale":"3人付款"},{"oldPrice":"118.00","sid":4334,"title":"天诺音韵白色加绒衬衫女长袖2019秋冬新品加厚保暖上衣韩版大码修身显瘦OL职业衬衣女士白领面试工作服","selfPrice":"购物返￥0","price":"118.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/b193ced5ef7b4cf0b213c6914f210f2d.jpg","isMember":0,"pid":14901,"isShow":1,"sale":"43人付款"},{"oldPrice":"198.00","sid":4334,"title":"晨芙长袖小西装女外套2019秋装职业装女装套装大学生面试商务正装气质白领工装大码休闲酒店美容师工作服","selfPrice":"购物返￥0","price":"178.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/c2133d0e95a54bb3beb904b60e7f5313.jpg","isMember":0,"pid":14900,"isShow":1,"sale":"21人付款"},{"oldPrice":"99.00","sid":4334,"title":"BIGTREE 2019夏季新款细跟高跟鞋 浅口尖头性感显瘦职业OL白领女单鞋 红色婚鞋伴娘鞋 黑色","selfPrice":"购物返￥0","price":"97.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/ccc8f9595cd840f3af91996dbfdcf486.jpg","isMember":0,"pid":14899,"isShow":1,"sale":"27人付款"},{"oldPrice":"499.00","sid":4339,"title":"飞利浦（PHILIPS）男士电动剃须刀多功能理容全身水洗剃胡刀胡须刀刮胡刀（配胡须修剪器）S5078","selfPrice":"购物返￥0","price":"469.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/069b28b3a10e4ae7814b10e24a3cb584.jpg","isMember":0,"pid":14976,"isShow":2,"sale":"0人付款"},{"oldPrice":"369.00","sid":4339,"title":"飞利浦(PHILIPS) 电动牙刷 成人声波震动牙刷(自带刷头*1) 机皇款 HX6730/02","selfPrice":"购物返￥0","price":"369.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/c9d6e613240a432686ec9577cddbf6fb.jpg","isMember":0,"pid":14973,"isShow":2,"sale":"0人付款"}],"pageSize":10,"pageNum":0}
     * code : 200
     * msg :
     */

    private DataBean data;
    private String code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * headImg : http://onecdndev.baozhen100.com/type/201911/f6b532af019648389db4170a3893f7cd.png
         * totalCount : 11
         * items : [{"oldPrice":"499.00","sid":4337,"title":"Gucci 古驰罪爱原罪女士淡香水50ml 柔和辛辣花香","selfPrice":"购物返￥0","price":"499.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/bbd3da6db9844e70a0c1faca102b54af.png","isMember":0,"pid":14954,"isShow":2,"sale":"13人付款"},{"oldPrice":"428.00","sid":4337,"title":"Gucci 古驰绚丽栀子花女士淡香水30／50／100ml花香调持久留香礼物","selfPrice":"购物返￥0","price":"428.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/45c4de80c2034518b8754c0552c5b255.png","isMember":0,"pid":14953,"isShow":2,"sale":"1人付款"},{"oldPrice":"2899.00","sid":4337,"title":"Dyson戴森V8 Absolute无线吸尘器顶配6吸头（2主／4配件吸头）美版","selfPrice":"购物返￥0","price":"2899.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/e92204cd5aec48aca16a7a3f7d12b6e7.png","isMember":0,"pid":14952,"isShow":2,"sale":"91人付款"},{"oldPrice":"1289.00","sid":4337,"title":"Danielwellington 丹尼尔惠灵顿dw手表女32mm编织钢带女表","selfPrice":"购物返￥0","price":"1289.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/550cab82c38d40c78a60894d707c1029.png","isMember":0,"pid":14951,"isShow":2,"sale":"3人付款"},{"oldPrice":"258.00","sid":4337,"title":"Dior／迪奥999哑光口红香水套装888 520 礼盒优惠套装","selfPrice":"购物返￥0","price":"258.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/cc62550606c14fab84ebbaf78293b335.png","isMember":0,"pid":14950,"isShow":2,"sale":"3人付款"},{"oldPrice":"118.00","sid":4334,"title":"天诺音韵白色加绒衬衫女长袖2019秋冬新品加厚保暖上衣韩版大码修身显瘦OL职业衬衣女士白领面试工作服","selfPrice":"购物返￥0","price":"118.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/b193ced5ef7b4cf0b213c6914f210f2d.jpg","isMember":0,"pid":14901,"isShow":1,"sale":"43人付款"},{"oldPrice":"198.00","sid":4334,"title":"晨芙长袖小西装女外套2019秋装职业装女装套装大学生面试商务正装气质白领工装大码休闲酒店美容师工作服","selfPrice":"购物返￥0","price":"178.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/c2133d0e95a54bb3beb904b60e7f5313.jpg","isMember":0,"pid":14900,"isShow":1,"sale":"21人付款"},{"oldPrice":"99.00","sid":4334,"title":"BIGTREE 2019夏季新款细跟高跟鞋 浅口尖头性感显瘦职业OL白领女单鞋 红色婚鞋伴娘鞋 黑色","selfPrice":"购物返￥0","price":"97.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/ccc8f9595cd840f3af91996dbfdcf486.jpg","isMember":0,"pid":14899,"isShow":1,"sale":"27人付款"},{"oldPrice":"499.00","sid":4339,"title":"飞利浦（PHILIPS）男士电动剃须刀多功能理容全身水洗剃胡刀胡须刀刮胡刀（配胡须修剪器）S5078","selfPrice":"购物返￥0","price":"469.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/069b28b3a10e4ae7814b10e24a3cb584.jpg","isMember":0,"pid":14976,"isShow":2,"sale":"0人付款"},{"oldPrice":"369.00","sid":4339,"title":"飞利浦(PHILIPS) 电动牙刷 成人声波震动牙刷(自带刷头*1) 机皇款 HX6730/02","selfPrice":"购物返￥0","price":"369.00","sharePrice":"分享赚￥0","img":"http://onecdndev.baozhen100.com/buyProduct/201911/c9d6e613240a432686ec9577cddbf6fb.jpg","isMember":0,"pid":14973,"isShow":2,"sale":"0人付款"}]
         * pageSize : 10
         * pageNum : 0
         */

        private String headImg;
        private int totalCount;
        private int pageSize;
        private int pageNum;
        private List<ItemsBean> items;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * oldPrice : 499.00
             * sid : 4337
             * title : Gucci 古驰罪爱原罪女士淡香水50ml 柔和辛辣花香
             * selfPrice : 购物返￥0
             * price : 499.00
             * sharePrice : 分享赚￥0
             * img : http://onecdndev.baozhen100.com/buyProduct/201911/bbd3da6db9844e70a0c1faca102b54af.png
             * isMember : 0
             * pid : 14954
             * isShow : 2
             * sale : 13人付款
             */

            private String oldPrice;
            private int sid;
            private String title;
            private String selfPrice;
            private String price;
            private String sharePrice;
            private String img;
            private int isMember;
            private int pid;
            private int isShow;
            private String sale;

            public String getOldPrice() {
                return oldPrice;
            }

            public void setOldPrice(String oldPrice) {
                this.oldPrice = oldPrice;
            }

            public int getSid() {
                return sid;
            }

            public void setSid(int sid) {
                this.sid = sid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSelfPrice() {
                return selfPrice;
            }

            public void setSelfPrice(String selfPrice) {
                this.selfPrice = selfPrice;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSharePrice() {
                return sharePrice;
            }

            public void setSharePrice(String sharePrice) {
                this.sharePrice = sharePrice;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getIsMember() {
                return isMember;
            }

            public void setIsMember(int isMember) {
                this.isMember = isMember;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public int getIsShow() {
                return isShow;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }

            public String getSale() {
                return sale;
            }

            public void setSale(String sale) {
                this.sale = sale;
            }
        }
    }
}
