package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityCreateUserBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

public class CreateUser extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";

    EditText mUser_name;
    EditText mPassword;
    EditText mConfirmPassword;
    Button mRegister;

    CheckBox mAdmin;

    SharedPreferences sharedPreferences;

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
        mAdmin = mCreateUserBinding.adminBox;



        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEntity userEntity = new UserEntity();
                if(mPassword.getText().toString().equals(mConfirmPassword.getText().toString()))
                {
                    userEntity.setName(mUser_name.getText().toString());
                    userEntity.setPassword(mPassword.getText().toString());
                    if(mAdmin.isChecked())
                    {
                        userEntity.setAdminStatus("yes");
                    } else {
                        userEntity.setAdminStatus("no");
                    }
                    if(validateInput(userEntity))
                    {
                        //UserEntity userEntity2

                        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                        final UserDao userDao = userDatabase.userDao();
                        if(userDao.getUser(userEntity.name)==null) {
                            userDao.registerUser(userEntity);
                            Toast.makeText(getApplicationContext(), "User Registered", Toast.LENGTH_SHORT).show();
                            /**
                             new Thread(new Runnable() {
                            @Override public void run() {
                            userDao.registerUser(userEntity);
                            Toast.makeText(getApplicationContext(),"User Registered", Toast.LENGTH_SHORT).show();
                            }
                            }).start();
                             **/
                            //Switching Intents
                            sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                            SharedPreferences .Editor editor = sharedPreferences.edit();
                            editor.putString("name","");
                            editor.apply();
                            Intent intent = MainActivity.getIntent(getApplicationContext());
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),"This username is in use already!",Toast.LENGTH_SHORT).show();
                        }
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