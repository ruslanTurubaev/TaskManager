package com.example.data.storage.models;

import java.util.Objects;

public class SignInByEmailResultStorageModel {
    private boolean isEmailVerified;
    private boolean isSignInSuccessful;
    private String notification;

    public SignInByEmailResultStorageModel() {
    }

    public SignInByEmailResultStorageModel(boolean isEmailVerified, boolean isSignInSuccessful, String notification) {
        this.isEmailVerified = isEmailVerified;
        this.isSignInSuccessful = isSignInSuccessful;
        this.notification = notification;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public boolean isSignInSuccessful() {
        return isSignInSuccessful;
    }

    public void setSignInSuccessful(boolean signInSuccessful) {
        isSignInSuccessful = signInSuccessful;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInByEmailResultStorageModel that = (SignInByEmailResultStorageModel) o;
        return isEmailVerified() == that.isEmailVerified() && isSignInSuccessful() == that.isSignInSuccessful() && Objects.equals(getNotification(), that.getNotification());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEmailVerified(), isSignInSuccessful(), getNotification());
    }
}
