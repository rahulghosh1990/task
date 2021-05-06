package com.example.singin.presentation;

import com.example.singin.view.DeshboardContract;
import com.example.singin.model.network.FetchDetailsFromServer;

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