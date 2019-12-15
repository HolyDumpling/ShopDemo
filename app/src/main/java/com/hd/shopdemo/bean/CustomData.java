package com.hd.shopdemo.bean;

public class CustomData {

    private String url;
    private String name;
    private String backColor;
    private boolean isMovie;
    private String type;
    private String gid;
    private String sid;
    private String link;

    public CustomData(String url, String name, String backColor, boolean isMovie) {
        this.url = url;
        this.name = name;
        this.backColor = backColor;
        this.isMovie = isMovie;
    }

    public CustomData(String url, String name, String backColor, boolean isMovie, String type, String gid, String sid, String link) {
        this.url = url;
        this.name = name;
        this.backColor = backColor;
        this.isMovie = isMovie;
        this.type = type;
        this.gid = gid;
        this.sid = sid;
        this.link = link;
    }

    public CustomData(String url, String name, boolean isMovie, String type, String gid, String sid) {
        this.url = url;
        this.name = name;
        this.isMovie = isMovie;
        this.type = type;
        this.gid = gid;
        this.sid = sid;
    }

    public String getBackColor() {
        return backColor;
    }

    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public boolean isMovie() {
        return isMovie;
    }

    public void setMovie(boolean movie) {
        isMovie = movie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
