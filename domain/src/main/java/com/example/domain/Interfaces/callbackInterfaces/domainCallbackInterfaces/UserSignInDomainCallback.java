package com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces;

import com.example.domain.models.SignInByEmailResultDomainModel;

public interface UserSignInDomainCallback {
    void onUserSignInDataResponse(SignInByEmailResultDomainModel response);
}
