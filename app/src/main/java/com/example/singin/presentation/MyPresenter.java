package com.example.singin.presentation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.singin.DeshboardContract;
import com.example.singin.SqliteHelperClass;
import com.example.singin.model.Entities;
import com.example.singin.model.network.FetchDetailsFromServer;

import java.util.List;

public class MyPresenter implements DeshboardContract.Presenter, DeshboardContract.Model.OnFinishedListner {

    private DeshboardContract.View list;

    private DeshboardContract.Model movieListModel;

    public MyPresenter(DeshboardContract.View list) {
        movieListModel = new FetchDetailsFromServer();
    }

    @Override
    public void onDestroy() {
        this.list = null;
    }




    @Override
    public void requestDataFromServer() {
        movieListModel.getresourses(this);
    }

}