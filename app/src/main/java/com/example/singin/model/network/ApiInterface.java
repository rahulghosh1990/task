package com.example.singin.model.network;

import com.example.singin.model.Entities;
import com.example.singin.model.Pagination;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


public interface ApiInterface {
    @Headers({"Content-Type: text/plain", "Accept: application/json"})
    @GET("public-api/categories")
    Call<Entities> getresourses();
}
