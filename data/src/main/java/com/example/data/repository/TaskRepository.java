package com.example.data.repository;

import com.example.data.storage.interfaces.callbackInterfaces.GetTaskDataCallback;
import com.example.data.storage.interfaces.storageIntrfaces.TaskStorageInterface;
import com.example.data.storage.models.TaskStorageModel;
import com.example.data.storage.models.TaskStorageResponseModel;
import com.example.domain.Interfaces.callbackInterfaces.domainCallbackInterfaces.GetTaskDomainCallback;
import com.example.domain.Interfaces.TaskRepositoryInterface;
import com.example.domain.models.TaskDomainModel;
import com.example.domain.models.TaskDomainResponseModel;

import java.util.ArrayList;

public class TaskRepository implements TaskRepositoryInterface, GetTaskDataCallback {
    private final TaskStorageInterface taskStorageInterface;
    private GetTaskDomainCallback callback;

    public TaskRepository(TaskStorageInterface taskStorageInterface) {
        this.taskStorageInterface = taskStorageInterface;
    }

    @Override
    public void saveTask(TaskDomainModel task) {
        TaskStorageModel taskStorageModel = taskDomainMapToStorage(task);
        taskStorageInterface.saveTask(taskStorageModel);
    }

    @Override
    public void getTasksByUser(GetTaskDomainCallback callback) {
        this.callback = callback;
        taskStorageInterface.getTasksByUser(this);
    }

    @Override
    public void getALllTasks(GetTaskDomainCallback callback) {
        this.callback = callback;
        taskStorageInterface.getAllTasks(this);
    }

    @Override
    public void getTask(String taskId, GetTaskDomainCallback callback) {
        this.callback = callback;
        taskStorageInterface.getTask(taskId, this);
    }

    @Override
    public void deleteTask(String taskId) {
        taskStorageInterface.deleteTask(taskId);
    }

    /*
    *callback's responses
     */
    @Override
    public void onGetTaskResponse(TaskStorageResponseModel response) {
        callback.OnGetTaskDataResponse(taskStorageResponseMapToDomain(response));
    }

    /*
     *Different mappers from storage models to domain models and back
     */
    private TaskDomainResponseModel taskStorageResponseMapToDomain(TaskStorageResponseModel response){
        if(response.isError()){
            return new TaskDomainResponseModel(true);
        }
        else {
            ArrayList<TaskDomainModel> tasksDomainArray = tasksArrayMapToDomain(response.getTaskStorageModelArrayList());
            return new TaskDomainResponseModel(tasksDomainArray);
        }
    }

    private TaskStorageModel taskDomainMapToStorage(TaskDomainModel task){
        TaskStorageModel taskStorageModel = new TaskStorageModel(task.getUserName(), task.getInputDocumentType(), task.getInputDocumentNumber(), task.getInputDocumentDate(), task.getInputDocumentContent(), task.getOutcomeDocumentType(), task.getOutcomeDocumentNumber(), task.getOutcomeDocumentDate(), task.getOutcomeDocumentContent(), task.getOutcomeDocumentAddress(), task.getOutcomeDocumentStatus(), task.getUserID());
        taskStorageModel.setTaskID(task.getTaskID());
        return taskStorageModel;
    }

    private TaskDomainModel taskStorageMapToDomain(TaskStorageModel task){
        TaskDomainModel taskDomainModel = new TaskDomainModel(task.getUserName(), task.getInputDocumentType(), task.getInputDocumentNumber(), task.getInputDocumentDate(), task.getInputDocumentContent(), task.getOutcomeDocumentType(), task.getOutcomeDocumentNumber(), task.getOutcomeDocumentDate(), task.getOutcomeDocumentContent(), task.getOutcomeDocumentAddress(), task.getOutcomeDocumentStatus(), task.getUserID());
        taskDomainModel.setTaskID(task.getTaskID());
        return taskDomainModel;
    }

    private ArrayList<TaskDomainModel> tasksArrayMapToDomain(ArrayList<TaskStorageModel> tasksStorageArray){
        ArrayList<TaskDomainModel> tasksDomainArray = new ArrayList<>();
        for (TaskStorageModel task : tasksStorageArray){
            TaskDomainModel taskDomainModel = taskStorageMapToDomain(task);
            tasksDomainArray.add(taskDomainModel);
        }
        return tasksDomainArray;
    }


}
