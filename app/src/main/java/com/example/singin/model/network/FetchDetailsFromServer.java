package com.example.singin.model.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.singin.DeshboardContract;
import com.example.singin.model.Entities;
import com.example.singin.model.Pagination;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchDetailsFromServer implements DeshboardContract.Model {
    int total, page;

    @Override
    public void getResourses(OnFinishedListner onFinishedListner) {
        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);
        Call<Entities> call = apiservice.getresourses();
        call.enqueue(new Callback<Entities>() {
            @Override
            public void onResponse(@NonNull Call<Entities> call, @NonNull Response<Entities> response) {
                assert response.body() != null;
                total = response.body().getCode();
            }

            @Override
            public void onFailure(@NonNull Call<Entities> call, @NonNull Throwable t) {
                Log.d("RAHUL_ERROR", t.toString());
            }
        });

    }
}
