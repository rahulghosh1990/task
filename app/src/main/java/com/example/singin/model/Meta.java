package com.example.singin.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meta {
    @SerializedName("pagination")
    private List< Pagination> paginations;

    public List<Pagination> getPaginations() {
        return paginations;
    }

    public void setPaginations(List<Pagination> paginations) {
        this.paginations = paginations;
    }
}
