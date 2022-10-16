package com.example.data.storage.interfaces.callbackInterfaces;

import com.example.data.storage.models.SignUpByEmailResultStorageModel;

public interface UserSignUpDataCallback {
    void onSignUpResponse(SignUpByEmailResultStorageModel response);
}
