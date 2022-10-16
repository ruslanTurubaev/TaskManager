package com.example.domain.Interfaces;

import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.GetUserProfileDomainCallback;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.UserSignInDomainCallback;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.UserSignUpDomainCallback;
import com.example.domain.models.UserIdDomainModel;
import com.example.domain.models.UserProfileDomainModel;
import com.example.domain.models.UserSignInDomainModel;
import com.example.domain.models.UserSignUpDomainModel;

public interface UserRepositoryInterface {
    void signInByEmail(UserSignInDomainModel userSignInDomainModel, UserSignInDomainCallback callback);
    void signUpByEmail(UserProfileDomainModel userProfileDomainModel, UserSignUpDomainModel userSignUpDomainModel, UserSignUpDomainCallback callback);
    void getUserProfile(GetUserProfileDomainCallback callback);
    boolean isCurrentUserExist();
    UserIdDomainModel getUserId();
    void signOut();
}
