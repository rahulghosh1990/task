package com.example.singin.model.network;

import android.util.Log;

import com.example.singin.DeshboardContract;
import com.example.singin.model.Pagination;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchDetailsFromServer implements DeshboardContract.Model {
    int total, page;

    @Override
    public void getResourses(OnFinishedListner onFinishedListner) {
        ApiInterface apiservice = ApiClient.getclient().create(ApiInterface.class);

        Call<Pagination> call = apiservice.getresourses();
        call.enqueue(new Callback<Pagination>() {
            @Override
            public void onResponse(Call<Pagination> call, Response<Pagination> response) {
                total = response.body().getTotal();
                page = response.body().getPage();
            }

            @Override
            public void onFailure(Call<Pagination> call, Throwable t) {
                Log.d("RAHUL_ERROR", t.toString());
            }
        });

    }
}
