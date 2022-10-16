package com.example.taskmanager.presentation.signUpActivity;

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
import com.example.taskmanager.presentation.loginActivity.LoginActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpActivity extends AppCompatActivity {
    private EditText edUserName, edEmail, edPassword, edPasswordConfirmation;

    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ConstraintLayout constraintLayout = findViewById(R.id.sign_up_background);

        edUserName = findViewById(R.id.ed_sign_up_user_name);
        edEmail = findViewById(R.id.ed_sign_up_email);
        edPassword = findViewById(R.id.ed_password);
        edPasswordConfirmation = findViewById(R.id.ed_confirm_password);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(30);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        signUpViewModel.getIsSignUpSuccessful().observe(this, isSignUpSuccessful ->{
            if(isSignUpSuccessful){
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickSignUp (View view){
        String userName = edUserName.getText().toString();
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        String passwordConfirmation = edPasswordConfirmation.getText().toString();

        signUpViewModel.signUp(userName ,email, password, passwordConfirmation, this);
    }

    public void onClickBack (View view){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
