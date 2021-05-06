package com.example.singin.presentation;

import com.example.singin.DeshboardContract;
import com.example.singin.model.DemoModel;

import java.util.List;

public interface DashboardContract {
    void returnData();
    interface View{
        void getList(List<DemoModel> data);
    }

}