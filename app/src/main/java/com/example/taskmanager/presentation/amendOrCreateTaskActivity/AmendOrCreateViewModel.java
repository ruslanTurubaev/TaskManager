package com.example.taskmanager.presentation.amendOrCreateTaskActivity;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.GetTaskCallback;
import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.GetUserProfileCallback;
import com.example.domain.models.TaskDomainModel;
import com.example.domain.models.TaskDomainResponseModel;
import com.example.domain.models.UserProfileDomainModel;
import com.example.domain.usecases.DeleteTaskUseCase;
import com.example.domain.usecases.GetTaskUseCase;
import com.example.domain.usecases.GetUserProfileUseCase;
import com.example.domain.usecases.IsMyTaskUseCase;
import com.example.domain.usecases.SetTaskUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AmendOrCreateViewModel extends ViewModel implements GetTaskCallback, GetUserProfileCallback {
    private SetTaskUseCase setTaskUseCase;
    private GetTaskUseCase getTaskUseCase;
    private DeleteTaskUseCase deleteTaskUseCase;
    private IsMyTaskUseCase isMyTaskUseCase;
    private GetUserProfileUseCase getUserProfileUseCase;

    private Context context;

    private MutableLiveData<TaskDomainModel> taskDomainModelMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isMyTaskMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> userNameMutableLiveData = new MutableLiveData<>();

    @Inject
    public AmendOrCreateViewModel(SetTaskUseCase setTaskUseCase, GetTaskUseCase getTaskUseCase, DeleteTaskUseCase deleteTaskUseCase, IsMyTaskUseCase isMyTaskUseCase, GetUserProfileUseCase getUserProfileUseCase) {
        this.setTaskUseCase = setTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.isMyTaskUseCase = isMyTaskUseCase;
        this.getUserProfileUseCase = getUserProfileUseCase;
    }

    public LiveData<TaskDomainModel> getTaskDomainModelMutableLiveData() {
        return taskDomainModelMutableLiveData;
    }

    public LiveData<Boolean> getIsMyTaskMutableLiveData() {
        return isMyTaskMutableLiveData;
    }

    public LiveData<String> getUserNameMutableLiveData() {
        return userNameMutableLiveData;
    }

    public void saveTask(Context context , String user, String inputDocumentType, String inputDocumentNumber, String inputDocumentDate, String inputDocumentContent, String outcomeDocumentType, String outcomeDocumentNumber, String outcomeDocumentDate, String outComeDocumentContent, String outcomeDocumentAddress, String outcomeDocumentStatus, String taskId){
        TaskDomainModel task = new TaskDomainModel(user, inputDocumentType, inputDocumentNumber, inputDocumentDate, inputDocumentContent, outcomeDocumentType, outcomeDocumentNumber, outcomeDocumentDate, outComeDocumentContent, outcomeDocumentAddress, outcomeDocumentStatus, "");
        task.setTaskID(taskId);

        setTaskUseCase.execute(task);

        if(taskId.equals("")){
           Toast.makeText(context, "A new task has been added", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "The task has been amended", Toast.LENGTH_SHORT).show();
        }
    }

    public void getTask(String taskID, Context context){
        this.context = context;
        getTaskUseCase.execute(taskID, this);
    }

    public void deleteTask(Context context, String taskId){
        deleteTaskUseCase.execute(taskId);
        Toast.makeText(context, "Task has been deleted", Toast.LENGTH_SHORT).show();
    }

    public void getUserName(){
        getUserProfileUseCase.execute(this);
    }

    /*
    *Callback from getTask request
     */
    @Override
    public void onGetTaskDomainResponse(TaskDomainResponseModel response) {
        if(response.isError()){
            Toast.makeText(context, "The task's downloading is failed", Toast.LENGTH_SHORT).show();
        }
        else {
            TaskDomainModel task = response.getTaskDomainModelArrayList().get(0);
            taskDomainModelMutableLiveData.setValue(task);
            String userId = task.getUserID();
            checkIsMyTask(userId);
        }
    }

    /*
    *compare user id in task model with current user id
    *if they are the same the current user is task's holder and able to make amendments in the task
     */
    private void checkIsMyTask(String userId){
        isMyTaskMutableLiveData.setValue(isMyTaskUseCase.execute(userId));
    }

    /*
     *Callback from getUserName request
     */
    @Override
    public void onGetUserProfileDomainResponse(UserProfileDomainModel userProfileDomainModel) {
        userNameMutableLiveData.setValue(userProfileDomainModel.getUserName());
    }
}
