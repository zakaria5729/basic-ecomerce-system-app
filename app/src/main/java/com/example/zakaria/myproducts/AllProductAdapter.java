package com.example.zakaria.myproducts;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zakaria.myproducts.models.MobileProduct;
import com.example.zakaria.myproducts.models.Product;

import java.util.ArrayList;
import java.util.List;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder> {

    Context context;
    List<MobileProduct> productList;

    public AllProductAdapter(Context context, List<MobileProduct> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public AllProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_product_layout, parent,false);
        return new AllProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductViewHolder holder, final int position) {
        final MobileProduct mobileProduct = productList.get(position);
        holder.companyName.setText(mobileProduct.getUserName());
        holder.productName.setText(mobileProduct.getProductName());
        holder.price.setText(mobileProduct.getPrice());
        Glide.with(context).
                load(mobileProduct.getImageUrl())
                .centerCrop()
                .override(130, 120)
                .into(holder.imageProdct);

        holder.imageProdct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ProductDetailsActivity.class);
                intent.putExtra("company_name", productList.get(position).getUserName());
                intent.putExtra("product_name", productList.get(position).getProductName());
                intent.putExtra("product_model", productList.get(position).getModel());
                intent.putExtra("product_brand", productList.get(position).getBrand());
                intent.putExtra("product_category", productList.get(position).getCategory());
                intent.putExtra("product_condition", productList.get(position).getCondition());
                intent.putExtra("product_location", productList.get(position).getLocation());
                intent.putExtra("product_posted_time", productList.get(position).getPosted());
                intent.putExtra("product_price", productList.get(position).getPrice());
                intent.putExtra("product_phone_number", productList.get(position).getPrice());
                intent.putExtra("product_image_url", mobileProduct.getImageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class AllProductViewHolder extends RecyclerView.ViewHolder{
        TextView companyName;
        TextView productName;
        TextView price;
        ImageView imageProdct;

        public AllProductViewHolder(View itemView) {
            super(itemView);
            companyName=itemView.findViewById(R.id.companyName);
            productName=itemView.findViewById(R.id.productName);
            price=itemView.findViewById(R.id.price);
            imageProdct=itemView.findViewById(R.id.imageProdct);
        }
    }
}
