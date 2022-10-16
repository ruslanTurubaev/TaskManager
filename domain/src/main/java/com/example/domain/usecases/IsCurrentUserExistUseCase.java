package com.example.domain.usecases;

import com.example.domain.Interfaces.UserRepositoryInterface;

public class IsCurrentUserExistUseCase {
    private UserRepositoryInterface userRepositoryInterface;

    public IsCurrentUserExistUseCase(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

    public boolean execute(){
        return userRepositoryInterface.isCurrentUserExist();
    }
}
