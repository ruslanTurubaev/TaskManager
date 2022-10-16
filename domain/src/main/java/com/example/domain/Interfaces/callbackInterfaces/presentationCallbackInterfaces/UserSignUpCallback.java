package com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces;

import com.example.domain.models.SignUpByEmailResultDomainModel;

public interface UserSignUpCallback {
    void onUserSignUpDomainResponse(SignUpByEmailResultDomainModel response);
}
