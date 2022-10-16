package com.example.data.storage.models;

import java.util.Objects;

public class UserProfileStorageModel {
    private String userName;
    private String email;

    public UserProfileStorageModel() {
    }

    public UserProfileStorageModel(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileStorageModel that = (UserProfileStorageModel) o;
        return Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getEmail());
    }
}
