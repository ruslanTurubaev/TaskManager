package com.example.taskmanager.presentation.logoActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskmanager.R;
import com.example.taskmanager.presentation.mainActivity.MainActivity;
import com.example.taskmanager.presentation.loginActivity.LoginActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LogoActivity extends AppCompatActivity {
    private LogoViewModel logoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        ConstraintLayout constraintLayout = findViewById(R.id.logo_background);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(30);
        animationDrawable.setExitFadeDuration(500);
        animationDrawable.start();

        logoViewModel = new ViewModelProvider(this).get(LogoViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logoViewModel.getIsCurrentUserExist().observe(this, this::runActivity);
    }

    private void runActivity(boolean isUserExist){
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent;
            if(isUserExist){
                intent = new Intent(LogoActivity.this, MainActivity.class);
            }
            else {
                intent = new Intent(LogoActivity.this, LoginActivity.class);
            }
            startActivity(intent);
        }, 2000);
    }
}
