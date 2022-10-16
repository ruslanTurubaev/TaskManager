package com.example.data.storage.interfaces.storageIntrfaces;

import com.example.data.storage.interfaces.callbackInterfaces.GetTaskDataCallback;
import com.example.data.storage.models.TaskStorageModel;

public interface TaskStorageInterface {
    void saveTask (TaskStorageModel task);
    void getTasksByUser(GetTaskDataCallback callback);
    void getAllTasks(GetTaskDataCallback callback);
    void getTask(String taskId, GetTaskDataCallback callback);
    void deleteTask(String taskId);
}
