package com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces;

import com.example.domain.models.SignInByEmailResultDomainModel;

public interface UserSignInCallback {
    void onUserSignInDomainResponse(SignInByEmailResultDomainModel response);
}
