package com.example.singin.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.singin.DeshboardContract;
import com.example.singin.R;
import com.example.singin.model.Entities;
import com.example.singin.model.network.FetchDetailsFromServer;

import java.util.ArrayList;

public class UserInterface extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Entities> data;
    FetchDetailsFromServer fetchDetailsFromServer;
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchDetailsFromServer=new FetchDetailsFromServer();
        fetchDetailsFromServer.getResourses((DeshboardContract.Model.OnFinishedListner) this);
        setContentView(R.layout.activity_user_interface);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<Entities>();

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}