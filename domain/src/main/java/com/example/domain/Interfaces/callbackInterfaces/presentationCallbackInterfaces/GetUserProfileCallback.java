package com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces;

import com.example.domain.models.UserProfileDomainModel;

public interface GetUserProfileCallback {
    void onGetUserProfileDomainResponse(UserProfileDomainModel userProfileDomainModel);
}
