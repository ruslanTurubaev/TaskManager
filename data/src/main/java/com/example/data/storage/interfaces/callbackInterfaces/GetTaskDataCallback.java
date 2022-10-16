package com.example.data.storage.interfaces.callbackInterfaces;

import com.example.data.storage.models.TaskStorageResponseModel;

public interface GetTaskDataCallback {
    void onGetTaskResponse(TaskStorageResponseModel response);

}
