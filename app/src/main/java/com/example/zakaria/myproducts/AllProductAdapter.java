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

import com.example.zakaria.myproducts.models.Product;

import java.util.ArrayList;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder> {

    Context context;
    ArrayList<Product> productList;


    public AllProductAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public AllProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_product_layout,parent,false);
        return new AllProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductViewHolder holder, final int position) {
        Product product =productList.get(position);
        holder.companyName.setText(product.getCompanyName());
        holder.productName.setText(product.getProductName());
        holder.price.setText(product.getPrice());
        holder.imageProdct.setImageResource(R.drawable.sample);

        holder.imageProdct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ProductDetailsActivity.class);
                intent.putExtra("company_name", productList.get(position).getCompanyName());
                intent.putExtra("product_name", productList.get(position).getProductName());
                intent.putExtra("price", productList.get(position).getPrice());

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
