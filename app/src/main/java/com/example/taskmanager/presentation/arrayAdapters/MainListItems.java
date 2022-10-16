package com.example.taskmanager.presentation.arrayAdapters;

import java.util.Objects;

public class MainListItems {
    private String description;
    private String status;
    private String id;

    public MainListItems(String title, String status, String id) {
        this.description = title;
        this.status = status;
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainListItems that = (MainListItems) o;
        return Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getStatus(), id);
    }
}
