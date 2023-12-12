package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartItems extends AppCompatActivity {

    ListView listView;
    ListView availableItems;
    List<ProductEntity> products = new ArrayList<>();
    //List<ProductEntity> availableProducts = new ArrayList<>();
    ItemBaseAdapter availableItemBaseAdapter;
    ItemBaseAdapter itemBaseAdapter;

    EditText mItemAdd;
    Button mItemButtonAdd;
    Button mPurchaseCart;

    UserDatabase userDatabase;
    UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);
        linkItems();
        prepareLists();
        mItemButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemAdd.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"You did not enter an item name",Toast.LENGTH_SHORT).show();
                } else {
                    products.add(new ProductEntity("Milk",12.99,"Cheese",1,1));
                    itemBaseAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void linkItems() {
        mItemAdd = findViewById(R.id.itemAddEditText);
        mItemButtonAdd = findViewById(R.id.addItemButton);
        mPurchaseCart = findViewById(R.id.purchaseCartButton);
        userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        userDao = userDatabase.userDao();
    }

    private void prepareLists() {
        listView = (ListView) findViewById(R.id.itemsListView);
        availableItems = (ListView) findViewById(R.id.availableItemsListView);
        availableItemBaseAdapter = new ItemBaseAdapter(getApplicationContext(),userDao.getProductsCartID(0));
        itemBaseAdapter = new ItemBaseAdapter(getApplicationContext(),products);
        listView.setAdapter(itemBaseAdapter);
        availableItems.setAdapter(availableItemBaseAdapter);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CartItems.class);
        return intent;
   }
}