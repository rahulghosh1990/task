package com.example.singin.presentation;

import com.example.singin.DeshboardContract;
import com.example.singin.model.Entities;
import com.example.singin.model.network.FetchDetailsFromServer;

import java.util.List;

public class MovieListPresenter implements DeshboardContract.Presenter, DeshboardContract.Model.OnFinishedListner {

    private DeshboardContract.View movieListView;

    private DeshboardContract.Model movieListModel;

    public MovieListPresenter(DeshboardContract.View movieListView) {
        this.movieListView = movieListView;
        movieListModel = new FetchDetailsFromServer();
    }

    @Override
    public void onDestroy() {
        this.movieListView = null;
    }

    @Override
    public void getMoreData(int pageNo) {

        movieListModel.getresourses(this);
    }

    @Override
    public void requestDataFromServer() {

        movieListModel.getresourses(this);

    }

/*    @Override
    public void onFinished(Entities entities) {
        movieListView.setDataToRecyclerView(entities);
      *//*  if (movieListView != null) {
            movieListView.hideProgress();
        }*//*
    }

    @Override
    public void onFinished(Entities entities) {

    }*/

    @Override
    public void onFinished(Entities entities) {

    }

    @Override
    public void onFailure(Throwable t) {

        movieListView.onResponseFailure(t);
        if (movieListView != null) {
            movieListView.hideProgress();
        }
    }
}