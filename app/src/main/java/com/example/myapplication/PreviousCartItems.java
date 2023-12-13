package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PreviousCartItems extends AppCompatActivity {
ListView listView;
public static final String SHARED_PREFS = "sharedPrefs";
List<ProductEntity> products = new ArrayList<>();
ItemBaseAdapter itemBaseAdapter;
TextView textView;
UserDatabase userDatabase;
UserDao userDao;
Integer userId;
Integer cartId;
SharedPreferences sharedPreferences;
SharedPreferences cartNamePreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_cart_items);
        linkItems();
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        cartNamePreferences = getSharedPreferences("CART_PREF",MODE_PRIVATE);
        textView.setText(cartNamePreferences.getString("cartName",""));
        String name = sharedPreferences.getString("name","");
        UserEntity idHolder = userDao.getUser(name);
        userId = idHolder.id;
        cartId = userDao.getSingularCartByUserIDandName(userId,cartNamePreferences.getString("cartName","")).getId();
        prepareLists();
        for(ProductEntity productEntity:userDao.getProductsCartIDUserID(cartId,userId))
        {
            products.add(productEntity);
        }
    }

    private void prepareLists() {
        listView = (ListView) findViewById(R.id.itemsListViewPrevious);
        itemBaseAdapter = new ItemBaseAdapter(getApplicationContext(),products);
        listView.setAdapter(itemBaseAdapter);
    }

    private void linkItems() {
        textView = findViewById(R.id.yourCartPrevious);
        userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        userDao = userDatabase.userDao();
    }

    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent(context, PreviousCartItems.class);
        return intent;
    }
}