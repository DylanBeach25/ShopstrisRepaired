package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityLandingPageBinding;

public class LandingPage extends AppCompatActivity {

    TextView mDisplayName;

    ActivityLandingPageBinding mLandingPageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        mLandingPageBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        View view = mLandingPageBinding.getRoot();

        setContentView(view);

        mDisplayName = mLandingPageBinding.displayName;

        mDisplayName.setText(getIntent().getStringExtra("displayName"));
    }
}