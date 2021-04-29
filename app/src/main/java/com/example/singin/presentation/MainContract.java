package com.example.singin.presentation;

import com.example.singin.model.Entities;

import java.util.ArrayList;

public interface MainContract {

    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }


    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Entities> noticeArrayList);

        void onResponseFailure(Throwable throwable);

    }


    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Entities> noticeArrayList);
            void onFailure(Throwable t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener);
    }
}
