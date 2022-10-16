package com.example.taskmanager.presentation.signUpActivity;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.UserSignUpCallback;
import com.example.domain.models.SignUpByEmailResultDomainModel;
import com.example.domain.models.UserSignUpDomainModel;
import com.example.domain.usecases.SignUpByEmailUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SignUpViewModel extends ViewModel implements UserSignUpCallback {
    private SignUpByEmailUseCase signUpByEmailUseCase;

    private MutableLiveData<Boolean> isSignUpSuccessful = new MutableLiveData<>(false);
    private Context context;

    public LiveData<Boolean> getIsSignUpSuccessful() {
        return isSignUpSuccessful;
    }

    @Inject
    public SignUpViewModel(SignUpByEmailUseCase signUpByEmailUseCase) {
        this.signUpByEmailUseCase = signUpByEmailUseCase;
    }

    public void signUp(String userName ,String email, String password, String confirmedPassword, Context context){
        this.context = context;
        UserSignUpDomainModel userSignUpDomainModel = new UserSignUpDomainModel(userName ,email, password, confirmedPassword);
        signUpByEmailUseCase.execute(userSignUpDomainModel, this);
    }

    @Override
    public void onUserSignUpDomainResponse(SignUpByEmailResultDomainModel response) {
        Toast.makeText(context, response.getNotification(), Toast.LENGTH_SHORT).show();
        isSignUpSuccessful.setValue(response.isSignUpSuccessful());
    }
}
