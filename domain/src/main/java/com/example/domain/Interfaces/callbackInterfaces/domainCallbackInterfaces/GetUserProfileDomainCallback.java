package com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces;

import com.example.domain.models.UserProfileDomainModel;

public interface GetUserProfileDomainCallback {
    void onGetUserProfileDataResponse(UserProfileDomainModel userProfileDomainModel);
}
