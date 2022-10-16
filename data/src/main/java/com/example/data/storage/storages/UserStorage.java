package com.example.data.storage.storages;

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
import com.example.data.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserStorage implements UserStorageInterface {

    @Override
    public void signInByEmail(UserSignInStorageModel userSignInStorageModel, UserSignInDataCallback callback) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        SignInByEmailResultStorageModel signInResult = new SignInByEmailResultStorageModel();

        auth.signInWithEmailAndPassword(userSignInStorageModel.getEmail(), userSignInStorageModel.getPassword()).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                signInResult.setSignInSuccessful(true);
                FirebaseUser currentUser = auth.getCurrentUser();
                assert currentUser != null;
                if (currentUser.isEmailVerified()){
                    signInResult.setEmailVerified(true);
                    signInResult.setNotification("You are entered with e-mail: " + userSignInStorageModel.getEmail());
                }
                else {
                    signInResult.setEmailVerified(false);
                    signInResult.setNotification("Please verify your e-mail");
                }
            }
            else {
                signInResult.setSignInSuccessful(false);
                signInResult.setEmailVerified(false);
                signInResult.setNotification("Singing in failed");
            }
            callback.onUserSignInResponse(signInResult);
        });
    }

    /*
    *user's sign up
    * if sign up is successful, then user information stored in the database
     */
    @Override
    public void singUpByEmail(UserProfileStorageModel userProfileStorageModel, UserSignUpStorageModel userSignUpStorageModel, UserSignUpDataCallback callback) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        SignUpByEmailResultStorageModel signUpResult = new SignUpByEmailResultStorageModel();
        auth.createUserWithEmailAndPassword(userSignUpStorageModel.getEmail(), userSignUpStorageModel.getPassword()).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                FirebaseUser currentUser = auth.getCurrentUser();
                assert currentUser != null;
                currentUser.sendEmailVerification();
                signUpResult.setSignUpSuccessful(true);
                signUpResult.setNotification("Please, verify your e-mail");
                saveUserProfile(userProfileStorageModel);
            }
            else {
                signUpResult.setSignUpSuccessful(false);
                signUpResult.setNotification("Signing up failed");
            }
            callback.onSignUpResponse(signUpResult);
        });
    }

    @Override
    public void getUserProfile(GetUserProfileDataCallback callback) {
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference(Constants.USERS).child(UID);

        mDatabase.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                UserProfileStorageModel userProfile = task.getResult().getValue(UserProfileStorageModel.class);
                callback.onGetUserProfileResponse(userProfile);
            }
        });

    }

    @Override
    public boolean isCurrentUserExist() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        return currentUser != null;
    }

    @Override
    public UserIdStorageModel getUserId() {
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return new UserIdStorageModel(UID);
    }

    @Override
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    /*
    *private method to store in database user's information
     */
    private void saveUserProfile(UserProfileStorageModel userProfileStorageModel){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        mDatabase.child(Constants.USERS).child(UID).setValue(userProfileStorageModel);
    }
}
