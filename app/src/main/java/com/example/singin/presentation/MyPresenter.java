package com.example.singin.presentation;

import com.example.singin.DeshboardContract;
import com.example.singin.model.Entities;
import com.example.singin.model.network.FetchDetailsFromServer;

import java.util.List;

public class MyPresenter implements DeshboardContract.Presenter, DeshboardContract.Model.OnFinishedListner {

    private DeshboardContract.View movieListView;

    private DeshboardContract.Model movieListModel;

    public MyPresenter(DeshboardContract.View movieListView) {
        this.movieListView = movieListView;
        movieListModel = new FetchDetailsFromServer();
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void requestDataFromServer() {
        movieListModel.getresourses(this);

    }

}