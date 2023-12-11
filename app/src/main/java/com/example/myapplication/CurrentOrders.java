package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityCurrentOrdersBinding;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrders extends AppCompatActivity implements SelectListener {

    public static final String SHARED_PREFS = "sharedPrefs";
    RecyclerView recyclerView;
    TextView textView;
    Button button;
    EditText editText;
    List<CartEntity> carts;

    UserDatabase userDatabase;
    UserDao userDao;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        userDao = userDatabase.userDao();

        setContentView(R.layout.activity_current_orders);

        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerView);
        textView = findViewById(R.id.orders_username);
        button = findViewById(R.id.createOrderButton);
        editText = findViewById(R.id.cartNameEditText);


        textView.setText(sharedPreferences.getString("name",""));

        carts = new ArrayList<>();
        /*
        carts.add(new CartEntity(1,"test1"));
        carts.add(new CartEntity(2,"test2"));
        carts.add(new CartEntity(3,"test3"));
        carts.add(new CartEntity(4,"test4"));
         */
        UserEntity passedUser = userDao.getUser(sharedPreferences.getString("name",""));
        carts = userDao.getCartsByID(passedUser.getId());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(),carts,this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCart();
            }
        });

    }

    private void createCart() {
        if(!editText.getText().toString().isEmpty())
        {
            UserEntity idHolder = userDao.getUser(sharedPreferences.getString("name",""));
            Integer userID = idHolder.getId();
            CartEntity trial = new CartEntity(userID,editText.getText().toString());
            carts.add(trial);
            recyclerView.getAdapter().notifyItemInserted(carts.size()-1);
            editText.getText().clear();
            recyclerView.scrollToPosition(carts.size()-1);
            userDao.registerCart(trial);
        } else {
            Toast.makeText(getApplicationContext(),"Please fill the order name field", Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent(context, CurrentOrders.class);
        return intent;
    }

    @Override
    public void onItemClicked(CartEntity cartEntity) {
        Intent intent  = CartItems.getIntent(getApplicationContext());
        startActivity(intent);
        //Toast.makeText(this,cartEntity.getName(),Toast.LENGTH_SHORT).show();
    }
}