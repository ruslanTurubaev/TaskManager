package com.example.domain.models;

import java.util.ArrayList;
import java.util.Objects;

public class TaskDomainResponseModel {
    private ArrayList<TaskDomainModel> taskDomainModelArrayList;
    private boolean isError = false;

    public TaskDomainResponseModel(boolean isError) {
        this.isError = isError;
    }

    public TaskDomainResponseModel(ArrayList<TaskDomainModel> taskDomainModelArrayList) {
        this.taskDomainModelArrayList = taskDomainModelArrayList;
    }

    public ArrayList<TaskDomainModel> getTaskDomainModelArrayList() {
        return taskDomainModelArrayList;
    }

    public void setTaskDomainModelArrayList(ArrayList<TaskDomainModel> taskDomainModelArrayList) {
        this.taskDomainModelArrayList = taskDomainModelArrayList;
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
        TaskDomainResponseModel that = (TaskDomainResponseModel) o;
        return isError() == that.isError() && Objects.equals(getTaskDomainModelArrayList(), that.getTaskDomainModelArrayList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskDomainModelArrayList(), isError());
    }
}
