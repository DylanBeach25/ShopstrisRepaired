package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CartItems extends AppCompatActivity {

    ListView listView;
    List<ProductEntity> products = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);
        products.add(new ProductEntity("Cheese",12.99,"Cheese",1,1,1));
        listView = (ListView) findViewById(R.id.itemsListView);
        ItemBaseAdapter itemBaseAdapter = new ItemBaseAdapter(getApplicationContext(),products);
        listView.setAdapter(itemBaseAdapter);
    }

   public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CartItems.class);
        return intent;
   }
}