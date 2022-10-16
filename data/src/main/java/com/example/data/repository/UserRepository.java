package com.example.data.repository;

import com.example.data.storage.interfaces.callbackInterfaces.GetUserProfileDataCallback;
import com.example.data.storage.interfaces.callbackInterfaces.UserSignInDataCallback;
import com.example.data.storage.interfaces.callbackInterfaces.UserSignUpDataCallback;
import com.example.data.storage.interfaces.storageIntrfaces.UserStorageInterface;
import com.example.data.storage.models.SignInByEmailResultStorageModel;
import com.example.data.storage.models.SignUpByEmailResultStorageModel;
import com.example.data.storage.models.UserIdStorageModel;
import com.example.data.storage.models.UserProfileStorageModel;
import com.example.data.storage.models.UserSignInStorageModel;
import com.example.data.storage.models.UserSignUpStorageModel;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.GetUserProfileDomainCallback;
import com.example.domain.Interfaces.UserRepositoryInterface;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.UserSignInDomainCallback;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.UserSignUpDomainCallback;
import com.example.domain.models.SignInByEmailResultDomainModel;
import com.example.domain.models.SignUpByEmailResultDomainModel;
import com.example.domain.models.UserIdDomainModel;
import com.example.domain.models.UserProfileDomainModel;
import com.example.domain.models.UserSignInDomainModel;
import com.example.domain.models.UserSignUpDomainModel;

public class UserRepository implements UserRepositoryInterface, UserSignInDataCallback, UserSignUpDataCallback, GetUserProfileDataCallback {
    private UserStorageInterface userStorageInterface;
    private UserSignInDomainCallback userSignInCallback;
    private UserSignUpDomainCallback userSignUpCallback;
    private GetUserProfileDomainCallback userProfileCallback;

    public UserRepository(UserStorageInterface userStorageInterface) {
        this.userStorageInterface = userStorageInterface;
    }

    @Override
    public void signInByEmail(UserSignInDomainModel userSignInDomainModel, UserSignInDomainCallback callback) {
        this.userSignInCallback = callback;
        UserSignInStorageModel userSignInStorageModel = userModelMapToStorage(userSignInDomainModel);
        userStorageInterface.signInByEmail(userSignInStorageModel, this);
    }

    @Override
    public void signUpByEmail(UserProfileDomainModel userProfileDomainModel, UserSignUpDomainModel userSignUpDomainModel, UserSignUpDomainCallback callback) {
        this.userSignUpCallback = callback;
        UserProfileStorageModel userProfileStorageModel = userProfileMapToStorage(userProfileDomainModel);
        UserSignUpStorageModel userSignUpStorageModel = userSignUpMapToStorage(userSignUpDomainModel);
        userStorageInterface.singUpByEmail(userProfileStorageModel ,userSignUpStorageModel, this);
    }

    @Override
    public void getUserProfile(GetUserProfileDomainCallback callback) {
        this.userProfileCallback = callback;
        userStorageInterface.getUserProfile(this);
    }

    @Override
    public boolean isCurrentUserExist() {
        return userStorageInterface.isCurrentUserExist();
    }

    @Override
    public UserIdDomainModel getUserId() {
        UserIdStorageModel userId = userStorageInterface.getUserId();
        return userIdMapToDomain(userId);
    }

    @Override
    public void signOut() {
        userStorageInterface.signOut();
    }

    /*
    *callbacks' responses
     */
    @Override
    public void onUserSignInResponse(SignInByEmailResultStorageModel response) {
        userSignInCallback.onUserSignInDataResponse(signInByEmailModelMapToDomain(response));
    }

    @Override
    public void onSignUpResponse(SignUpByEmailResultStorageModel response) {
        userSignUpCallback.onUserSignUpDataResponse(signUpByEmailResultMapToDomain(response));
    }

    @Override
    public void onGetUserProfileResponse(UserProfileStorageModel response) {
        userProfileCallback.onGetUserProfileDataResponse(userProfileMapToDomain(response));
    }

    /*
     *Different mappers from storage models to domain models and back
     */
    private UserIdDomainModel userIdMapToDomain(UserIdStorageModel userId){
        return new UserIdDomainModel(userId.getUserId());
    }

    private UserProfileStorageModel userProfileMapToStorage(UserProfileDomainModel userProfileDomainModel){
        return new UserProfileStorageModel(userProfileDomainModel.getUserName(), userProfileDomainModel.getEmail());
    }

    private UserProfileDomainModel userProfileMapToDomain(UserProfileStorageModel userProfileStorageModel){
        return new UserProfileDomainModel(userProfileStorageModel.getUserName(), userProfileStorageModel.getEmail());
    }

    private UserSignInStorageModel userModelMapToStorage(UserSignInDomainModel userSignInDomainModel){
        return new UserSignInStorageModel(userSignInDomainModel.getEmail(), userSignInDomainModel.getPassword());
    }

    private UserSignUpStorageModel userSignUpMapToStorage(UserSignUpDomainModel userSignUpDomainModel){
        return new UserSignUpStorageModel(userSignUpDomainModel.getEmail(), userSignUpDomainModel.getPassword(), userSignUpDomainModel.getConfirmedPassword());
    }

    private SignInByEmailResultDomainModel signInByEmailModelMapToDomain(SignInByEmailResultStorageModel signInByEmailResultStorageModel){
        return new SignInByEmailResultDomainModel(signInByEmailResultStorageModel.isEmailVerified(), signInByEmailResultStorageModel.isSignInSuccessful(), signInByEmailResultStorageModel.getNotification());
    }

    private SignUpByEmailResultDomainModel signUpByEmailResultMapToDomain(SignUpByEmailResultStorageModel signUpByEmailResultStorageModel){
        return new SignUpByEmailResultDomainModel(signUpByEmailResultStorageModel.isSignUpSuccessful(), signUpByEmailResultStorageModel.getNotification());
    }
}
