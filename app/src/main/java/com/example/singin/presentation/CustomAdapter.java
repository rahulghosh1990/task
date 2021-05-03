package com.example.singin.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.singin.model.DemoModel;
import com.example.singin.R;

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
        View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_card, parent, false);
        DemoViewHolder gvh = new DemoViewHolder(groceryProductView);
        return gvh;
    }

    @Override
    public void onBindViewHolder(DemoViewHolder holder, final int position) {
        holder.imageProductImage.setImageResource(demoModels.get(position).getProductImage());
        holder.txtProductPrice.setText(demoModels.get(position).getProductPrice());
        holder.txtProductName.setText(demoModels.get(position).getProductQty());
        holder.txtProductWeight.setText(demoModels.get(position).getProductWeight());
        holder.imageProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productPrice = demoModels.get(position).getProductPrice();
                Toast.makeText(context, productPrice + " is selected", Toast.LENGTH_SHORT).show();
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
        public DemoViewHolder(View view) {
            super(view);
            imageProductImage=view.findViewById(R.id.imageView);
            txtProductName=view.findViewById(R.id.textViewName);
            txtProductPrice = view.findViewById(R.id.mynamr);
            txtProductWeight = view.findViewById(R.id.textViewVersion);
        }
    }}