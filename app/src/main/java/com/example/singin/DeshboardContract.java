package com.example.singin;

import com.example.singin.model.Entities;
import com.example.singin.model.Pagination;

import java.util.List;

public interface DeshboardContract {
    interface Model{
        interface OnFinishedListner{
        }
        void getresourses(OnFinishedListner onFinishedListner);
    }
    interface View{
    }

    interface Presenter{
        void onDestroy();
        void requestDataFromServer();
    }
}
