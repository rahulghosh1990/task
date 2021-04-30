package com.example.singin.presentation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.singin.model.Data;
import com.example.singin.model.Entities;
import com.example.singin.model.network.ApiClient;
import com.example.singin.model.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class GetNoticeIntractorImpl implements MainContract.GetNoticeIntractor {

    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener) {



        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);


        Call<Entities> call = service.getresourses();


        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<Entities>() {
            @Override
            public void onResponse(@NonNull Call<Entities> call, @NonNull Response<Entities> response) {
                assert response.body() != null;
            }
            @Override
            public void onFailure(Call<Entities> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
