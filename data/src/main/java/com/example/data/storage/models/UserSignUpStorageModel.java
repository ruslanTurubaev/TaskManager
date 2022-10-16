package com.example.data.storage.models;

import java.util.Objects;

public class UserSignUpStorageModel {
    private String email;
    private String password;
    private String confirmedPassword;

    public UserSignUpStorageModel(String email, String password, String confirmedPassword) {
        this.email = email;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSignUpStorageModel that = (UserSignUpStorageModel) o;
        return Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getConfirmedPassword(), that.getConfirmedPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getConfirmedPassword());
    }
}
