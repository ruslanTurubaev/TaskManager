package com.example.data.storage.models;

import java.util.ArrayList;
import java.util.Objects;

public class TaskStorageResponseModel {
    private ArrayList<TaskStorageModel> taskStorageModelArrayList;
    private boolean isError = false;

    public TaskStorageResponseModel(boolean isError) {
        this.isError = isError;
    }

    public TaskStorageResponseModel(ArrayList<TaskStorageModel> taskStorageModelArrayList) {
        this.taskStorageModelArrayList = taskStorageModelArrayList;
    }

    public ArrayList<TaskStorageModel> getTaskStorageModelArrayList() {
        return taskStorageModelArrayList;
    }

    public void setTaskStorageModelArrayList(ArrayList<TaskStorageModel> taskStorageModelArrayList) {
        this.taskStorageModelArrayList = taskStorageModelArrayList;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStorageResponseModel that = (TaskStorageResponseModel) o;
        return isError() == that.isError() && Objects.equals(getTaskStorageModelArrayList(), that.getTaskStorageModelArrayList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskStorageModelArrayList(), isError());
    }
}
