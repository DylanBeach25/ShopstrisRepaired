package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminTools extends AppCompatActivity {

    Button mStockItem;
    Button mRemoveItem;

    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tools);
        mStockItem = findViewById(R.id.StockItemButton);
        mRemoveItem = findViewById(R.id.removeItemButton);
        backButton = findViewById(R.id.adminToolsBackButton);

        mStockItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = StockItem.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
        mRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RemoveItems.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LandingPage.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });
    }


    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, AdminTools.class);
        return intent;
    }
}