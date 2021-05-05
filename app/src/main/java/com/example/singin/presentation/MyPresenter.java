package com.example.singin.presentation;

import com.example.singin.DeshboardContract;
import com.example.singin.model.Entities;
import com.example.singin.model.network.FetchDetailsFromServer;

import java.util.List;

public class MyPresenter implements DeshboardContract.Presenter, DeshboardContract.Model.OnFinishedListner {

    private DeshboardContract.View view;

    private DeshboardContract.Model movieListModel;

    public MyPresenter(DeshboardContract.View list ) {
        movieListModel = new FetchDetailsFromServer();
    }
    @Override
    public void requestDataFromServer() {
        movieListModel.getresourses(this);
    }
}