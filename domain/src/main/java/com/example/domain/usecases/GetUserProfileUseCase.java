package com.example.domain.usecases;

import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.GetUserProfileCallback;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.GetUserProfileDomainCallback;
import com.example.domain.Interfaces.UserRepositoryInterface;
import com.example.domain.models.UserProfileDomainModel;

public class GetUserProfileUseCase implements GetUserProfileDomainCallback {
    private UserRepositoryInterface userRepositoryInterface;
    private GetUserProfileCallback callback;

    public GetUserProfileUseCase(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

    public void execute(GetUserProfileCallback callback){
        this.callback = callback;
        userRepositoryInterface.getUserProfile(this);
    }

    @Override
    public void onGetUserProfileDataResponse(UserProfileDomainModel userProfileDomainModel) {
        callback.onGetUserProfileDomainResponse(userProfileDomainModel);
    }
}
