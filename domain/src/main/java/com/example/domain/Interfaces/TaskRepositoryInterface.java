package com.example.domain.Interfaces;

import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.GetTaskDomainCallback;
import com.example.domain.models.TaskDomainModel;

public interface TaskRepositoryInterface {
    void saveTask(TaskDomainModel task);
    void getTasksByUser(GetTaskDomainCallback callback);
    void getALllTasks(GetTaskDomainCallback callback);
    void getTask(String taskId, GetTaskDomainCallback callback);
    void deleteTask(String taskId);
}
