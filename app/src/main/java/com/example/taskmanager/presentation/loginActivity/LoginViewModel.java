package com.example.taskmanager.presentation.loginActivity;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.UserSignInCallback;
import com.example.domain.models.SignInByEmailResultDomainModel;
import com.example.domain.models.UserSignInDomainModel;
import com.example.domain.usecases.SignInByEmailUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel implements UserSignInCallback {
    private SignInByEmailUseCase signInByEmailUseCase;
    private MutableLiveData<Boolean> isSignInSuccessful = new MutableLiveData<>(false);
    private Context context;

    public LiveData<Boolean> getIsSignInSuccessful() {
        return isSignInSuccessful;
    }

    @Inject
    public LoginViewModel(SignInByEmailUseCase signInByEmailUseCase) {
        this.signInByEmailUseCase = signInByEmailUseCase;
    }

    public void signIn (String email, String password, Context context){
        this.context = context;
        UserSignInDomainModel userSignIn = new UserSignInDomainModel(email, password);
        signInByEmailUseCase.execute(userSignIn, this);
    }

    @Override
    public void onUserSignInDomainResponse(SignInByEmailResultDomainModel response) {
        Toast.makeText(context, response.getNotification(), Toast.LENGTH_SHORT).show();
        if(response.isSignInSuccessful() && response.isEmailVerified()){
            isSignInSuccessful.setValue(true);
        }
    }
}
