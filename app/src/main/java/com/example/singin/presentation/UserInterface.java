package com.example.singin.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.singin.DeshboardContract;
import com.example.singin.R;
import com.example.singin.model.Entities;
import com.example.singin.model.Pagination;

import java.util.ArrayList;
import java.util.List;

public class UserInterface extends AppCompatActivity implements DeshboardContract.View {

    private static final String TAG = "MovieListActivity";
    private MovieListPresenter movieListPresenter;
    private RecyclerView rvMovieList;
    private Entities entities;
    private CustomAdapter moviesAdapter;
    //private ProgressBar pbLoading;
    private TextView tvEmptyView;


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
        movieListPresenter = new MovieListPresenter(this);

        movieListPresenter.requestDataFromServer();
    }

    private void initUI() {
        rvMovieList = findViewById(R.id.my_recycler_view);
        entities = new Entities();
       // moviesAdapter = new CustomAdapter(entities);
        mLayoutManager = new LinearLayoutManager(this);
        rvMovieList.setLayoutManager(mLayoutManager);
        rvMovieList.setItemAnimator(new DefaultItemAnimator());
       // rvMovieList.setAdapter(moviesAdapter);
       // pbLoading = findViewById(R.id.prgr_br);
    }

 /*   @Override
    public void showProgress() {

        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        pbLoading.setVisibility(View.GONE);
    }*/

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
        movieListPresenter.onDestroy();
    }


}