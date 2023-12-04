package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    public static final String SHARED_PREFS = "sharedPrefs";

    ActivityMainBinding mMainBinding;
    UserDatabase userDatabase;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        userDao = userDatabase.userDao();

        checkBox();
        checkUsers();


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
                    //UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    //UserDao userDao = userDatabase.userDao();
                    UserEntity userEntity = userDao.login(name,password);
                    if(userEntity == null) {
                        Toast.makeText(getApplicationContext(),"Invalid Credentials!",Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("name",userEntity.name);
                        editor.apply();

                        String displayName = userEntity.name;
                        startActivity(new Intent(MainActivity.this,LandingPage.class).putExtra("displayName",displayName));

                    }
                }
            }
        });
    }

    private void checkUsers() {
        if(userDao.getAllUsers().size() < 1)
        {
            UserEntity testUser1 = new UserEntity();
            UserEntity admin2 = new UserEntity();
            testUser1.setName("testuser1");
            testUser1.setPassword("testuser1");
            testUser1.setAdminStatus("no");
            admin2.setName("admin2");
            admin2.setPassword("admin2");
            admin2.setAdminStatus("yes");
            userDao.registerUser(testUser1);
            userDao.registerUser(admin2);
        }

    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        String check = sharedPreferences.getString("name","");
        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
        UserDao userDao = userDatabase.userDao();
        if(userDao.getUser(check)!=null) {
            startActivity(new Intent(MainActivity.this,LandingPage.class).putExtra("displayName",check));
            finish();
        }
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}