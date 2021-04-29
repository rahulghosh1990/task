package com.example.singin.model.network;

import com.example.singin.model.Data;
import com.example.singin.model.Entities;
import com.example.singin.model.Pagination;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface ApiInterface {
    @GET("public-api/categories")
    Call<Entities> getresourses();
}
