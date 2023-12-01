package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.databinding.ActivityCreateUserBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

public class CreateUser extends AppCompatActivity {

    EditText mUser_name;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mRegister;

    ActivityCreateUserBinding mCreateUserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        mCreateUserBinding = ActivityCreateUserBinding.inflate(getLayoutInflater());
        View view = mCreateUserBinding.getRoot();

        setContentView(view);

        mRegister = mCreateUserBinding.button;
        mConfirmPassword = mCreateUserBinding.confirmPassword;
        mPassword = mCreateUserBinding.password;
        mUser_name = mCreateUserBinding.userName;

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CreateUser.class);
        return intent;
    }

}