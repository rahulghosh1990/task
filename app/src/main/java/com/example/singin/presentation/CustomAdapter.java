package com.example.singin.presentation;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.singin.R;
import com.example.singin.model.Entities;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Entities dataList;
   // private RecyclerItemClickListener recyclerItemClickListener;

    public CustomAdapter(Entities dataList ) {
        this.dataList = dataList;
        //this.recyclerItemClickListener = recyclerItemClickListener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cards_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txtNoticeTitle.setText(dataList.getData().get(position).getName());
        //holder.txtNoticeBrief.setText(dataList.get(position).getBrief());
        //holder.txtNoticeFilePath.setText(dataList.get(position).getFileSource());
    }

    @Override
    public int getItemCount() {
        return dataList.getData().size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNoticeTitle, txtNoticeBrief, txtNoticeFilePath;
        ImageView imageView;


        MyViewHolder(View itemView) {
            super(itemView);
            txtNoticeTitle =  itemView.findViewById(R.id.textViewName);
            txtNoticeBrief =  itemView.findViewById(R.id.textViewVersion);
        }
    }
}