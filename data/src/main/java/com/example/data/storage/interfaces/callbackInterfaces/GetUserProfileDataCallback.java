package com.example.data.storage.interfaces.callbackInterfaces;

import com.example.data.storage.models.UserProfileStorageModel;

public interface GetUserProfileDataCallback {
    void onGetUserProfileResponse(UserProfileStorageModel response);
}
