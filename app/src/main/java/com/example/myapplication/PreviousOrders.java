package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PreviousOrders extends AppCompatActivity implements SelectListener{
    public static final String SHARED_PREFS = "sharedPrefs";
    RecyclerView recyclerView;
    TextView textView;

    List<CartEntity> carts;

    UserDao userDao;
    UserDatabase userDatabase;

    SharedPreferences sharedPreferences;
    SharedPreferences cartNamePreferences;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_orders);

        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        cartNamePreferences = getSharedPreferences("CART_PREF",MODE_PRIVATE);
        userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        userDao = userDatabase.userDao();

        recyclerView = findViewById(R.id.previousOrdersRecyclerView);
        textView = findViewById(R.id.previous_orders_username);

        textView.setText(sharedPreferences.getString("name",""));
        UserEntity passedUser = userDao.getUser(sharedPreferences.getString("name",""));
        carts = userDao.getCartsByCartState(passedUser.getId(),"yes");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),carts,this));
    }

    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent (context, PreviousOrders.class);
        return intent;
    }

    @Override
    public void onItemClicked(CartEntity cartEntity) {
        Toast.makeText(getApplicationContext(),"This is a test for functionality",Toast.LENGTH_SHORT).show();
    }
}