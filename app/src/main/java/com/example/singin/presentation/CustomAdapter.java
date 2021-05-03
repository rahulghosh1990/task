package com.example.singin.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.singin.DemoModel;
import com.example.singin.R;
import com.example.singin.model.Entities;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.DemoViewHolder>{
    private List<DemoModel> demoModels;
    Context context;

    public CustomAdapter(List<DemoModel> demoModels, Context context) {
        this.demoModels = demoModels;
        this.context = context;
    }

    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_card, parent, false);
        DemoViewHolder gvh = new DemoViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, final int position) {
        holder.imageProductImage.setImageResource(demoModels.get(position).getProductImage());
        holder.txtProductName.setText(demoModels.get(position).getProductName());
        holder.txtProductPrice.setText(demoModels.get(position).getProductPrice());
        holder.txtProductWeight.setText(demoModels.get(position).getProductWeight());
        holder.txtProductQty.setText(demoModels.get(position).getProductQty());

        holder.imageProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = demoModels.get(position).getProductName().toString();
                Toast.makeText(context, productName + " is selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return demoModels.size();
    }

    public class DemoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProductImage;
        TextView txtProductName;
        TextView txtProductPrice;
        TextView txtProductWeight;
        TextView txtProductQty;
        public DemoViewHolder(View view) {
            super(view);
            imageProductImage=view.findViewById(R.id.idProductImage);
            txtProductName=view.findViewById(R.id.idProductName);
            txtProductPrice = view.findViewById(R.id.idProductPrice);
            txtProductWeight = view.findViewById(R.id.idProductWeight);
            txtProductQty = view.findViewById(R.id.idProductQty);
        }
    }}