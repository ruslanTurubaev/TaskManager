package com.example.data.storage.interfaces.storageIntrfaces;

import com.example.data.storage.interfaces.callbackInterfaces.GetUserProfileDataCallback;
import com.example.data.storage.interfaces.callbackInterfaces.UserSignInDataCallback;
import com.example.data.storage.interfaces.callbackInterfaces.UserSignUpDataCallback;
import com.example.data.storage.models.UserIdStorageModel;
import com.example.data.storage.models.UserProfileStorageModel;
import com.example.data.storage.models.UserSignInStorageModel;
import com.example.data.storage.models.UserSignUpStorageModel;

public interface UserStorageInterface {
    void signInByEmail(UserSignInStorageModel userSignInStorageModel, UserSignInDataCallback callback);
    void singUpByEmail(UserProfileStorageModel userProfileStorageModel, UserSignUpStorageModel userSignUpStorageModel, UserSignUpDataCallback callback);
    void getUserProfile(GetUserProfileDataCallback callback);
    boolean isCurrentUserExist();
    UserIdStorageModel getUserId();
    void signOut();
}
