package com.example.singin.presentation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.singin.model.DemoModel;
import com.example.singin.DeshboardContract;
import com.example.singin.R;
import com.example.singin.model.Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeshBoard extends AppCompatActivity implements DeshboardContract.View {

    private static final String TAG = "MovieListActivity";
    private MyPresenter myPresenter;
    private RecyclerView myreCyclerView;
    private CustomAdapter customAdapter;
    private List<DemoModel> mProductList;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(R.string.dash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
        initUI();
        myPresenter = new MyPresenter(this);
        myPresenter.requestDataFromServer();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void initUI() {
        myreCyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        myreCyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProductList = new ArrayList<>();
        mProductList.add(new DemoModel(R.drawable.comment, "u Rapid", "Today, 4.57pm", "50"));
        mProductList.add(new DemoModel(R.drawable.gas_station, "g", "Today, 4.57pm", "100"));
        mProductList.add(new DemoModel(R.drawable.smartphone, "min", "Today, 4.57pm", "30"));
        mProductList.add(new DemoModel(R.drawable.messenger, "mgdl", "Today, 4.57pm", "200"));
        mProductList.add(new DemoModel(R.drawable.headphone_symbol, "u Rapid", "Today, 4.57pm", "50"));
        mProductList.add(new DemoModel(R.drawable.gas_station, "g", "Today, 4.57pm", "200"));
        customAdapter = new CustomAdapter(mProductList, this);
        myreCyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, "Communication Error", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPresenter.onDestroy();
    }
}