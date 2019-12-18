package com.hd.shopdemo.ui.classify;

public class ClassifyLeftItem {
    int c_id;
    String c_title;
    boolean isSelected;

    public ClassifyLeftItem(int c_id, String c_title, boolean isSelected) {
        this.c_id = c_id;
        this.c_title = c_title;
        this.isSelected = isSelected;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_title() {
        return c_title;
    }

    public void setC_title(String c_title) {
        this.c_title = c_title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
