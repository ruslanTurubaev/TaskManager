package com.example.data.storage.interfaces.callbackInterfaces;

import com.example.data.storage.models.SignInByEmailResultStorageModel;

public interface UserSignInDataCallback {
    void onUserSignInResponse (SignInByEmailResultStorageModel response);
}
