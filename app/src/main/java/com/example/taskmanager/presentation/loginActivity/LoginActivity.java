package com.example.taskmanager.presentation.loginActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.taskmanager.R;
import com.example.taskmanager.presentation.mainActivity.MainActivity;
import com.example.taskmanager.presentation.signUpActivity.SignUpActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private EditText edEmail, edPassword;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ConstraintLayout constraintLayout = findViewById(R.id.background);
        edEmail = findViewById(R.id.editTextTextEmailAddress);
        edPassword = findViewById(R.id.editTextTextPassword);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(30);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getIsSignInSuccessful().observe(this, isSignInSuccessful ->{
            if(isSignInSuccessful){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickSignIn (View view){
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();

        loginViewModel.signIn(email, password, this);
    }

    public void onClickSignUp (View view){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
