package com.example.singin.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.singin.model.DemoModel;
import com.example.singin.DeshboardContract;
import com.example.singin.R;
import com.example.singin.model.Entities;

import java.util.ArrayList;
import java.util.List;

public class UserInterface extends AppCompatActivity implements DeshboardContract.View {

    private static final String TAG = "MovieListActivity";
    private MyPresenter myPresenter;
    private RecyclerView myreCyclerView;
    private Entities entities;
    private CustomAdapter customAdapter;
    private ProgressBar pbLoading;
    private TextView tvEmptyView;
    private List<DemoModel> mProductList;

    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
        initUI();
        //Initializing presenter
        myPresenter = new MyPresenter(this);

        myPresenter.requestDataFromServer();
    }

    private void initUI() {
        myreCyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //mRecyclerView.setHasFixedSize(true);
        myreCyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Populate the products
        mProductList = new ArrayList<>();
        mProductList.add(new DemoModel("Demo1",R.drawable.ic_launcher_background,"Rs. 150", "1 kg", "5"));
        mProductList.add(new DemoModel("Demo2",R.drawable.ic_launcher_background,"Rs. 250", "500 gm", "2"));
        mProductList.add(new DemoModel("Demo3",R.drawable.ic_launcher_background,"Rs. 250", "500 gm", "2"));
        mProductList.add(new DemoModel("Demo4",R.drawable.ic_launcher_background,"Rs. 250", "500 gm", "2"));
        mProductList.add(new DemoModel("Demo5",R.drawable.ic_launcher_background,"Rs. 250", "500 gm", "2"));
        //set adapter to recyclerview
        customAdapter = new CustomAdapter(mProductList,this);
        myreCyclerView.setAdapter(customAdapter);
       // rvMovieList.setAdapter(moviesAdapter);
       // pbLoading = findViewById(R.id.prgr_br);
    }

    @Override
    public void showProgress() {

        //pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

       // pbLoading.setVisibility(View.GONE);
    }


    @Override
    public void setDataToRecyclerView(Entities entities) {

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