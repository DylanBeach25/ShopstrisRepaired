package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityLandingPageBinding;

public class LandingPage extends AppCompatActivity {

    TextView mDisplayName;
    Button mLogoutButton;

    public static final String SHARED_PREFS = "sharedPrefs";

    ActivityLandingPageBinding mLandingPageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mLandingPageBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        View view = mLandingPageBinding.getRoot();

        setContentView(view);

        mDisplayName = mLandingPageBinding.displayName;
        mLogoutButton = mLandingPageBinding.logoutButton;

        mDisplayName.setText(sharedPreferences.getString("name",""));
        //mDisplayName.setText(getIntent().getStringExtra("displayName"));

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("name","");
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}