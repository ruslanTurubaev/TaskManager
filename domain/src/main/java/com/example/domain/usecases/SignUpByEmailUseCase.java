package com.example.domain.usecases;

import com.example.domain.Interfaces.UserRepositoryInterface;
import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.UserSignUpCallback;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.UserSignUpDomainCallback;
import com.example.domain.models.SignUpByEmailResultDomainModel;
import com.example.domain.models.UserProfileDomainModel;
import com.example.domain.models.UserSignUpDomainModel;

public class SignUpByEmailUseCase implements UserSignUpDomainCallback {
    private UserRepositoryInterface userRepositoryInterface;
    private UserSignUpCallback callback;

    public SignUpByEmailUseCase(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

    public void execute(UserSignUpDomainModel userSignUpDomainModel, UserSignUpCallback callback){
        this.callback = callback;
        /*
        *Checking whether user provides all required information.
         */
        if(userSignUpDomainModel.getUserName().isEmpty()){
            onUserSignUpDataResponse(new SignUpByEmailResultDomainModel(false, "Please provide your user name"));
        }
        else if(userSignUpDomainModel.getEmail().isEmpty()){
            onUserSignUpDataResponse(new SignUpByEmailResultDomainModel(false, "Please provide your e-mail"));
        }
        else if(userSignUpDomainModel.getPassword().isEmpty()){
            onUserSignUpDataResponse(new SignUpByEmailResultDomainModel(false, "Please provide your password"));
        }
        else if(!userSignUpDomainModel.getPassword().equals(userSignUpDomainModel.getConfirmedPassword())){
            onUserSignUpDataResponse(new SignUpByEmailResultDomainModel(false, "Your password is not confirmed"));
        }
        else {
            UserProfileDomainModel userProfileDomainModel = new UserProfileDomainModel(userSignUpDomainModel.getUserName(), userSignUpDomainModel.getEmail());
            userRepositoryInterface.signUpByEmail(userProfileDomainModel ,userSignUpDomainModel, this);
        }
    }

    @Override
    public void onUserSignUpDataResponse(SignUpByEmailResultDomainModel response) {
        callback.onUserSignUpDomainResponse(response);
    }
}
