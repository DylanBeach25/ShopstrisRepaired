package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.databinding.ActivityCurrentOrdersBinding;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrders extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_current_orders);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<CartEntity> carts = new ArrayList<>();
        carts.add(new CartEntity(1,"test1"));
        carts.add(new CartEntity(2,"test2"));
        carts.add(new CartEntity(3,"test3"));
        carts.add(new CartEntity(4,"test4"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),carts));

    }

    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent(context, CurrentOrders.class);
        return intent;
    }
}