package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Button mRegister_user;
    Button mLogin;
    EditText mUsername;
    EditText mPassword;

    ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mMainBinding.getRoot();

        setContentView(view);

        mRegister_user = mMainBinding.registerUserButton;
        mLogin = mMainBinding.loginButton;
        mUsername = mMainBinding.loginUsername;
        mPassword = mMainBinding.loginPassword;

        mRegister_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CreateUser.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                if(name.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Fill all fields",Toast.LENGTH_SHORT).show();
                } else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    UserEntity userEntity = userDao.login(name,password);
                    if(userEntity == null) {
                        Toast.makeText(getApplicationContext(),"Invalid Credentials!",Toast.LENGTH_SHORT).show();
                    } else {
                        //Come back to once I make a login activity
                        String displayName = userEntity.name;
                        startActivity(new Intent(MainActivity.this,LandingPage.class).putExtra("displayName",displayName));

                    }
                }
            }
        });
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}