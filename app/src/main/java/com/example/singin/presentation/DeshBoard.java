package com.example.singin.presentation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.singin.model.DemoModel;
import com.example.singin.DeshboardContract;
import com.example.singin.R;
import com.example.singin.model.Entities;
import com.example.singin.model.network.FetchDetailsFromServer;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeshBoard extends AppCompatActivity implements PresenterDashboard.View {
    private RecyclerView myreCyclerView;
    private CustomAdapter customAdapter;
    private List<DemoModel> mProductList;
    PresenterDashboard presenterDashboard;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(R.string.dash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
        initUI();
        new FetchDetailsFromServer();
        presenterDashboard = new PresenterDashboard((DashboardContract.View) this);
        presenterDashboard.returnData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initUI() {
        myreCyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        myreCyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getList(List<DemoModel> data) {
        mProductList=data;
        customAdapter = new CustomAdapter(mProductList, this);
        myreCyclerView.setAdapter(customAdapter);
    }
}