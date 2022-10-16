package com.example.domain.usecases;

import com.example.domain.Interfaces.UserRepositoryInterface;
import com.example.domain.models.UserIdDomainModel;

public class IsMyTaskUseCase {
    private UserRepositoryInterface userRepositoryInterface;

    public IsMyTaskUseCase(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

    public boolean execute(String userId){
        UserIdDomainModel userIdDomainModel = userRepositoryInterface.getUserId();
        return userId.equals(userIdDomainModel.getUserId());
    }
}
