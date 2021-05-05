package com.example.singin.model;

import com.example.singin.DeshboardContract;
import com.example.singin.model.network.FetchDetailsFromServer;

public class ApiOperation implements DeshboardContract.Model {

    @Override
    public void getresourses(OnFinishedListner onFinishedListner) {
        new FetchDetailsFromServer();
    }
}
