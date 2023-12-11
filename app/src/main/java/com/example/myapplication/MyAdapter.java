package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<CartEntity> carts;
    SelectListener listener;

    public MyAdapter(Context context, List<CartEntity> carts, SelectListener listener) {
        this.context = context;
        this.carts = carts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mCartName.setText(carts.get(position).getName());

        holder.cartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.onItemClicked(carts.get(position));
                listener.onItemClicked(carts.get(holder.getAbsoluteAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
