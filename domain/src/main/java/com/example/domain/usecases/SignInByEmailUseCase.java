package com.example.domain.usecases;

import com.example.domain.Interfaces.UserRepositoryInterface;
import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.UserSignInCallback;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.UserSignInDomainCallback;
import com.example.domain.models.SignInByEmailResultDomainModel;
import com.example.domain.models.UserSignInDomainModel;

public class SignInByEmailUseCase implements UserSignInDomainCallback {
    private UserRepositoryInterface userRepositoryInterface;
    private UserSignInCallback callback;

    public SignInByEmailUseCase(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

    public void execute(UserSignInDomainModel userSignInDomainModel, UserSignInCallback callback){
        this.callback = callback;
        /*
        *Checking whether user provides with its email and password.
         */
        if(userSignInDomainModel.getEmail().isEmpty()){
            onUserSignInDataResponse(new  SignInByEmailResultDomainModel(false, false, "Please provide your e-mail"));
        }
        else if(userSignInDomainModel.getPassword().isEmpty()){
            onUserSignInDataResponse(new  SignInByEmailResultDomainModel(false, false, "Please provide your password"));
        }
        else {
            userRepositoryInterface.signInByEmail(userSignInDomainModel, this);
        }
    }

    @Override
    public void onUserSignInDataResponse(SignInByEmailResultDomainModel response) {
        callback.onUserSignInDomainResponse(response);
    }
}
