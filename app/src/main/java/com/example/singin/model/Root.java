package com.example.singin.model;

import java.util.List;

public class Root {
    private int code;

    private Meta meta;

    private List<Data> data;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMeta(Meta meta){
        this.meta = meta;
    }
    public Meta getMeta(){
        return this.meta;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
}
