package com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces;

import com.example.domain.models.SignUpByEmailResultDomainModel;

public interface UserSignUpDomainCallback {
    void onUserSignUpDataResponse(SignUpByEmailResultDomainModel response);
}
