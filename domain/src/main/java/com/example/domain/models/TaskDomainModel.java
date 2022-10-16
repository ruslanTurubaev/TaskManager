package com.example.domain.models;

import java.util.Objects;

public class TaskDomainModel {
    private String taskID;
    private String userName;
    private String inputDocumentType;
    private String inputDocumentNumber;
    private String inputDocumentDate;
    private String inputDocumentContent;
    private String outcomeDocumentType;
    private String outcomeDocumentNumber;
    private String outcomeDocumentDate;
    private String outcomeDocumentContent;
    private String outcomeDocumentAddress;
    private String outcomeDocumentStatus;
    private String userID;

    public TaskDomainModel(String userName, String inputDocumentType, String inputDocumentNumber, String inputDocumentDate, String inputDocumentContent, String outcomeDocumentType, String outcomeDocumentNumber, String outcomeDocumentDate, String outcomeDocumentContent, String outcomeDocumentAddress, String outcomeDocumentStatus, String userId) {
        this.userName = userName;
        this.inputDocumentType = inputDocumentType;
        this.inputDocumentNumber = inputDocumentNumber;
        this.inputDocumentDate = inputDocumentDate;
        this.inputDocumentContent = inputDocumentContent;
        this.outcomeDocumentType = outcomeDocumentType;
        this.outcomeDocumentNumber = outcomeDocumentNumber;
        this.outcomeDocumentDate = outcomeDocumentDate;
        this.outcomeDocumentContent = outcomeDocumentContent;
        this.outcomeDocumentAddress = outcomeDocumentAddress;
        this.outcomeDocumentStatus = outcomeDocumentStatus;
        this.userID = userId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInputDocumentType() {
        return inputDocumentType;
    }

    public void setInputDocumentType(String inputDocumentType) {
        this.inputDocumentType = inputDocumentType;
    }

    public String getInputDocumentNumber() {
        return inputDocumentNumber;
    }

    public void setInputDocumentNumber(String inputDocumentNumber) {
        this.inputDocumentNumber = inputDocumentNumber;
    }

    public String getInputDocumentDate() {
        return inputDocumentDate;
    }

    public void setInputDocumentDate(String inputDocumentDate) {
        this.inputDocumentDate = inputDocumentDate;
    }

    public String getInputDocumentContent() {
        return inputDocumentContent;
    }

    public void setInputDocumentContent(String inputDocumentContent) {
        this.inputDocumentContent = inputDocumentContent;
    }

    public String getOutcomeDocumentType() {
        return outcomeDocumentType;
    }

    public void setOutcomeDocumentType(String outcomeDocumentType) {
        this.outcomeDocumentType = outcomeDocumentType;
    }

    public String getOutcomeDocumentNumber() {
        return outcomeDocumentNumber;
    }

    public void setOutcomeDocumentNumber(String outcomeDocumentNumber) {
        this.outcomeDocumentNumber = outcomeDocumentNumber;
    }

    public String getOutcomeDocumentDate() {
        return outcomeDocumentDate;
    }

    public void setOutcomeDocumentDate(String outcomeDocumentDate) {
        this.outcomeDocumentDate = outcomeDocumentDate;
    }

    public String getOutcomeDocumentContent() {
        return outcomeDocumentContent;
    }

    public void setOutcomeDocumentContent(String outcomeDocumentContent) {
        this.outcomeDocumentContent = outcomeDocumentContent;
    }

    public String getOutcomeDocumentAddress() {
        return outcomeDocumentAddress;
    }

    public void setOutcomeDocumentAddress(String outcomeDocumentAddress) {
        this.outcomeDocumentAddress = outcomeDocumentAddress;
    }

    public String getOutcomeDocumentStatus() {
        return outcomeDocumentStatus;
    }

    public void setOutcomeDocumentStatus(String outcomeDocumentStatus) {
        this.outcomeDocumentStatus = outcomeDocumentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDomainModel that = (TaskDomainModel) o;
        return Objects.equals(getTaskID(), that.getTaskID()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getInputDocumentType(), that.getInputDocumentType()) && Objects.equals(getInputDocumentNumber(), that.getInputDocumentNumber()) && Objects.equals(getInputDocumentDate(), that.getInputDocumentDate()) && Objects.equals(getInputDocumentContent(), that.getInputDocumentContent()) && Objects.equals(getOutcomeDocumentType(), that.getOutcomeDocumentType()) && Objects.equals(getOutcomeDocumentNumber(), that.getOutcomeDocumentNumber()) && Objects.equals(getOutcomeDocumentDate(), that.getOutcomeDocumentDate()) && Objects.equals(getOutcomeDocumentContent(), that.getOutcomeDocumentContent()) && Objects.equals(getOutcomeDocumentAddress(), that.getOutcomeDocumentAddress()) && Objects.equals(getOutcomeDocumentStatus(), that.getOutcomeDocumentStatus()) && Objects.equals(getUserID(), that.getUserID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskID(), getUserName(), getInputDocumentType(), getInputDocumentNumber(), getInputDocumentDate(), getInputDocumentContent(), getOutcomeDocumentType(), getOutcomeDocumentNumber(), getOutcomeDocumentDate(), getOutcomeDocumentContent(), getOutcomeDocumentAddress(), getOutcomeDocumentStatus(), getUserID());
    }
}
