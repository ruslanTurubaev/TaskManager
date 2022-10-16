package com.example.domain.usecases;

import com.example.domain.Interfaces.TaskRepositoryInterface;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.GetTaskDomainCallback;
import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.GetTaskCallback;
import com.example.domain.models.TaskDomainResponseModel;

public class GetTasksByUserUseCase implements GetTaskDomainCallback {
    private TaskRepositoryInterface taskRepositoryInterface;
    private GetTaskCallback callback;

    public GetTasksByUserUseCase(TaskRepositoryInterface taskRepositoryInterface) {
        this.taskRepositoryInterface = taskRepositoryInterface;
    }

    public void execute(GetTaskCallback callback){
        this.callback = callback;
        taskRepositoryInterface.getTasksByUser(this);
    }

    @Override
    public void OnGetTaskDataResponse(TaskDomainResponseModel response) {
        callback.onGetTaskDomainResponse(response);
    }
}
