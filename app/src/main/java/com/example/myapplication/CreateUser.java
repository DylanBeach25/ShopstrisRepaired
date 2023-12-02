package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                UserEntity userEntity = new UserEntity();
                if(mPassword.getText().toString().equals(mConfirmPassword.getText().toString()))
                {
                    userEntity.setName(mUser_name.getText().toString());
                    userEntity.setPassword(mPassword.getText().toString());
                    if(validateInput(userEntity))
                    {
                        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                        final UserDao userDao = userDatabase.userDao();

                        userDao.registerUser(userEntity);
                        Toast.makeText(getApplicationContext(),"User Registered", Toast.LENGTH_SHORT).show();
                        /**
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                userDao.registerUser(userEntity);
                                Toast.makeText(getApplicationContext(),"User Registered", Toast.LENGTH_SHORT).show();
                            }
                        }).start();
                         **/
                        //Switching Intents
                        Intent intent = MainActivity.getIntent(getApplicationContext());
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),"Please fill all fields!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Your passwords do not match",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Boolean validateInput(UserEntity userEntity) {
        if(userEntity.getName().isEmpty() || userEntity.getPassword().isEmpty())
        {
            return false;
        }
        return true;
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, CreateUser.class);
        return intent;
    }

}