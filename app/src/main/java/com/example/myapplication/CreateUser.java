package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateUser extends AppCompatActivity {

    EditText mUser_name;
    EditText mPassword;
    Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CreateUser.class);
        return intent;
    }

}