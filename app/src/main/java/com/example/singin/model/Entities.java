package com.example.singin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Entities {
    @SerializedName("code")
    private int code;
    @SerializedName("meta")
    private List< Meta> meta;
    @SerializedName("data")
    private List<Data> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Meta> getMeta() {
        return meta;
    }

    public void setMeta(List<Meta> meta) {
        this.meta = meta;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
