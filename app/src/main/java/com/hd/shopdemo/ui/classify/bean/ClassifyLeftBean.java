package com.hd.shopdemo.ui.classify.bean;

import java.util.List;

public class ClassifyLeftBean {


    private List<ClassifyDataListBean> classifyDataList;

    public List<ClassifyDataListBean> getClassifyDataList() {
        return classifyDataList;
    }

    public void setClassifyDataList(List<ClassifyDataListBean> classifyDataList) {
        this.classifyDataList = classifyDataList;
    }

    public static class ClassifyDataListBean {
        /**
         * g_id : 0
         * s_img : null
         * s_id : null
         * s_title : 标题0
         * type : 0
         */

        private int g_id;
        private Object s_img;
        private Object s_id;
        private String s_title;
        private int type;

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public Object getS_img() {
            return s_img;
        }

        public void setS_img(Object s_img) {
            this.s_img = s_img;
        }

        public Object getS_id() {
            return s_id;
        }

        public void setS_id(Object s_id) {
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
}
