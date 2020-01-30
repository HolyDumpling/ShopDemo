package com.hd.shopdemo.bean;

import com.google.gson.Gson;

public class BaseBean<T> {
    private T data;
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getJSON_Str() {
        return new Gson().toJson(this);
    }
}
