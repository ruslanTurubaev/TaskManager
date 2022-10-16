package com.example.domain.models;

import java.util.Objects;

public class SignUpByEmailResultDomainModel {
    private boolean isSignUpSuccessful;
    private String notification;

    public SignUpByEmailResultDomainModel(boolean isSignUpSuccessful, String notification) {
        this.isSignUpSuccessful = isSignUpSuccessful;
        this.notification = notification;
    }

    public boolean isSignUpSuccessful() {
        return isSignUpSuccessful;
    }

    public void setSignUpSuccessful(boolean signUpSuccessful) {
        isSignUpSuccessful = signUpSuccessful;
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
        SignUpByEmailResultDomainModel that = (SignUpByEmailResultDomainModel) o;
        return isSignUpSuccessful() == that.isSignUpSuccessful() && Objects.equals(getNotification(), that.getNotification());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSignUpSuccessful(), getNotification());
    }
}
