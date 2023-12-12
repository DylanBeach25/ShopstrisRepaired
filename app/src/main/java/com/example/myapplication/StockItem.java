package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StockItem extends AppCompatActivity {
    EditText mItemName;
    EditText mItemPrice;
    EditText mItemDescription;
    Button mAddItem;

    UserDatabase userDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_item);
        mItemName = findViewById(R.id.ProductNameEditText);
        mItemPrice = findViewById(R.id.PriceEditText);
        mItemDescription = findViewById(R.id.DescriptionEditText);
        mAddItem = findViewById(R.id.ConfirmStockItemButton);
        userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        userDao = userDatabase.userDao();

        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitItem();
            }
        });
    }

    private void submitItem() {
        String name = mItemName.getText().toString();
        String description = mItemDescription.getText().toString();
        String priceString = mItemPrice.getText().toString();
        if(name.isEmpty()||description.isEmpty()||priceString.isEmpty())
        {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            Double price = Double.parseDouble(priceString);
            if(userDao.getProductsNameCartID(name,0).size() ==0) {
                ProductEntity productInsert = new ProductEntity(name, price, description, 0, 0);
                userDao.addProduct(productInsert);
                Toast.makeText(this,"Item stocked",Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this,"This item is already stocked", Toast.LENGTH_SHORT).show();

        }
    }

    public static Intent getIntent(Context context) {
    Intent intent = new Intent(context, StockItem.class);
    return intent;
    }
}