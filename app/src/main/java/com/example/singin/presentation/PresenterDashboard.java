package com.example.singin.presentation;

import com.example.singin.R;
import com.example.singin.SqliteHelperClass;
import com.example.singin.model.DemoModel;

import java.util.ArrayList;
import java.util.List;

public class PresenterDashboard implements DashboardContract {
    List<DemoModel> list;
    DashboardContract.View view;

    public PresenterDashboard( DashboardContract.View view) {
        this.view = view;
    }


    @Override
    public void returnData() {
        list = new ArrayList<>();
        list.add(new DemoModel(R.drawable.comment, "u Rapid", "Today, 4.57pm", "50"));
        list.add(new DemoModel(R.drawable.gas_station, "g", "Today, 4.57pm", "100"));
        list.add(new DemoModel(R.drawable.smartphone, "min", "Today, 4.57pm", "30"));
        list.add(new DemoModel(R.drawable.messenger, "mgdl", "Today, 4.57pm", "200"));
        list.add(new DemoModel(R.drawable.headphone_symbol, "u Rapid", "Today, 4.57pm", "50"));
        list.add(new DemoModel(R.drawable.gas_station, "g", "Today, 4.57pm", "200"));

   view.getList(list);
    }


   /* @Override
    public void setData(List<DemoModel> list) {

    }*/


}
