package com.example.singin.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pagination {
    @SerializedName("total")
    private int total;
    @SerializedName("pages")
    private int pages;
    @SerializedName("page")
    private int page;
    @SerializedName("limit")
    private int limit;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
