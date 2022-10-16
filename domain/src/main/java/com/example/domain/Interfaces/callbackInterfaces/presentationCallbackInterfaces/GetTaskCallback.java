package com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces;

import com.example.domain.models.TaskDomainResponseModel;

public interface GetTaskCallback {
    void onGetTaskDomainResponse(TaskDomainResponseModel response);

}
