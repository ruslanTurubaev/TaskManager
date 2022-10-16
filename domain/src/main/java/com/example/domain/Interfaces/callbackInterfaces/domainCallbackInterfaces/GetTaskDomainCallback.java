package com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces;

import com.example.domain.models.TaskDomainResponseModel;

public interface GetTaskDomainCallback {
    void OnGetTaskDataResponse(TaskDomainResponseModel response);

}
