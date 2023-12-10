package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView mCartName;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        mCartName = itemView.findViewById(R.id.cartName);
    }
}
