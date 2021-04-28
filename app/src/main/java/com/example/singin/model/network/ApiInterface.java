package com.example.singin.model.network;

import com.example.singin.model.Meta;
import com.example.singin.model.Pagination;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/public-api/categories")
    Call<Pagination> getresourses();
}
