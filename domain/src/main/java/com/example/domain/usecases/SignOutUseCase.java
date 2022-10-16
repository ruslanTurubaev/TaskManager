package com.example.domain.usecases;

import com.example.domain.Interfaces.UserRepositoryInterface;

public class SignOutUseCase {
    private UserRepositoryInterface userRepositoryInterface;

    public SignOutUseCase(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

    public void execute(){
        userRepositoryInterface.signOut();
    }
}
