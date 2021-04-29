package com.example.singin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meta {
    @SerializedName("pagination")
    private Pagination paginations;

    public Pagination getPaginations() {
        return paginations;
    }

    public void setPaginations(Pagination paginations) {
        this.paginations = paginations;
    }
}
