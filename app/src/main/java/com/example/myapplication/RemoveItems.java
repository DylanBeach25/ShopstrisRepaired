package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class RemoveItems extends AppCompatActivity {
    EditText mRemoveItemEditText;
    Button mRemoveItemButton;

    ListView mRemoveItemListView;

    ItemBaseAdapter availableItemBaseAdapter;

    UserDatabase userDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_items);

        linkItems();

        mRemoveItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mRemoveItemEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must enter an item to delete", Toast.LENGTH_SHORT).show();
                } else {
                    ProductEntity available = userDao.getSingularProductNameCartID(mRemoveItemEditText.getText().toString(),0);
                    if(available==null)
                    {
                        Toast.makeText(RemoveItems.this, "This item isn't available for removal", Toast.LENGTH_SHORT).show();
                    } else {
                        userDao.deleteItem(mRemoveItemEditText.getText().toString(),0);
                        Toast.makeText(getApplicationContext(), "Item successfully removed", Toast.LENGTH_SHORT).show();
                        Intent intent = AdminTools.getIntent(getApplicationContext());
                        startActivity(intent);
                    }

                }

            }
        });


    }

    private void linkItems() {
        mRemoveItemEditText = findViewById(R.id.removeItemEditText);
        mRemoveItemButton = findViewById(R.id.deleteItemButton);
        mRemoveItemListView = (ListView)findViewById(R.id.removeItemListView);
        userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        userDao = userDatabase.userDao();
        availableItemBaseAdapter = new ItemBaseAdapter(getApplicationContext(),userDao.getProductsCartID(0));
        mRemoveItemListView.setAdapter(availableItemBaseAdapter);

    }

    public static Intent getIntent(Context context)
    {
        Intent intent = new Intent(context,RemoveItems.class);
        return intent;
    }
}