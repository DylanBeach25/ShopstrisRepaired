package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CartItems extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    ListView listView;
    ListView availableItems;
    List<ProductEntity> products = new ArrayList<>();
    //List<ProductEntity> availableProducts = new ArrayList<>();
    ItemBaseAdapter availableItemBaseAdapter;
    ItemBaseAdapter itemBaseAdapter;

    EditText mItemAdd;
    Button mItemButtonAdd;
    Button mPurchaseCart;
    TextView mUserName;

    UserDatabase userDatabase;
    UserDao userDao;
    Integer userId;
    Integer cartID;

    SharedPreferences sharedPreferences;
    SharedPreferences cartNamePreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_items);
        linkItems();
        sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        cartNamePreferences = getSharedPreferences("CART_PREF",MODE_PRIVATE);
        mUserName.setText(cartNamePreferences.getString("cartName",""));
        String name = sharedPreferences.getString("name","");
        UserEntity idHolder = userDao.getUser(name);
        userId = idHolder.id;
        cartID = userDao.getSingularCartByUserIDandName(userId,cartNamePreferences.getString("cartName","")).getId();
        prepareLists();
        mUserName.setText(sharedPreferences.getString("name",""));
        for(ProductEntity productEntity: userDao.getProductsCartIDUserID(cartID,userId))
        {
            products.add(productEntity);
        }
        //products = userDao.getProductsCartIDUserID(cartID,userId);

        mItemButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemAdd.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"You did not enter an item name",Toast.LENGTH_SHORT).show();
                } else {
                    if(!userDao.getProductsNameCartID(mItemAdd.getText().toString(),0).isEmpty()) {
                        ProductEntity dataTranfer = userDao.getProductsNameCartID(mItemAdd.getText().toString(),0).get(0);

                        ProductEntity addedProduct = new ProductEntity(dataTranfer.getProductName(), dataTranfer.getProductPrice(), dataTranfer.getDescription(),userId,cartID);
                        userDao.addProduct(addedProduct);
                        products.add(addedProduct);
                        //products.add(new ProductEntity("Milk", 12.99, "Cheese", 1, 1));
                        itemBaseAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(),"This item is not in stock",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        mPurchaseCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.updateCart("yes", userId, cartID);
                Toast.makeText(getApplicationContext(),"Cart has been purchased",Toast.LENGTH_SHORT).show();
                Intent intent = CurrentOrders.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    private void linkItems() {
        mItemAdd = findViewById(R.id.itemAddEditText);
        mItemButtonAdd = findViewById(R.id.addItemButton);
        mPurchaseCart = findViewById(R.id.purchaseCartButton);
        mUserName = findViewById(R.id.ShoppingCartUserName);
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