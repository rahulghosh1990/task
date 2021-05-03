package com.example.singin;

import com.example.singin.model.Entities;
import com.example.singin.model.Pagination;

import java.util.List;

public interface DeshboardContract {
    interface Model{
        interface OnFinishedListner{
            void onFinished(Entities entities);
            void onFailure(Throwable t);
        }
        void getresourses(OnFinishedListner onFinishedListner);
    }

    interface View{
        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(Entities entities);
        void onResponseFailure(Throwable throwable);
    }

    interface Presenter{
        void onDestroy();
        void getMoreData(int pageNo);
        void requestDataFromServer();
    }
}
