package com.example.singin;

import com.example.singin.model.Pagination;

import java.util.List;

public interface DeshboardContract {
    interface Model{
        interface OnFinishedListner{
            void onFinished(Pagination pagination);
            void onFailure(Throwable t);
        }
        void getResourses(OnFinishedListner onFinishedListner);
    }

    interface View{
        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(Pagination pagination);
        void onResponseFailure(Throwable throwable);
    }

    interface Presenter{
        void onDestroy();
        void requestDataFromServer();

    }
}
