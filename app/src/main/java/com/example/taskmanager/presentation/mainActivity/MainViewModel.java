package com.example.taskmanager.presentation.mainActivity;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.Interfaces.callbackInterfaces.presentationCallbackInterfaces.GetTaskCallback;
import com.example.domain.models.TaskDomainModel;
import com.example.domain.models.TaskDomainResponseModel;
import com.example.domain.usecases.ChangeSettingsUseCase;
import com.example.domain.usecases.GetAllTasksUseCase;
import com.example.domain.usecases.GetCurrentSettingsUseCase;
import com.example.domain.usecases.GetTasksByUserUseCase;
import com.example.domain.usecases.SignOutUseCase;
import com.example.taskmanager.presentation.arrayAdapters.MainListItems;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;

@HiltViewModel
public class MainViewModel extends ViewModel implements GetTaskCallback {
    private GetTasksByUserUseCase getTasksByUserUseCase;
    private SignOutUseCase signOutUseCase;
    private GetAllTasksUseCase getAllTasksUseCase;
    private GetCurrentSettingsUseCase getCurrentSettingsUseCase;
    private ChangeSettingsUseCase changeSettingsUseCase;

    private Context context;

    private MutableLiveData<ArrayList<MainListItems>> itemsLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isMyTasks = new MutableLiveData<>(true);

    @Inject
    public MainViewModel(@ApplicationContext Context context, GetTasksByUserUseCase getTasksByUserUseCase, SignOutUseCase signOutUseCase, GetAllTasksUseCase getAllTasksUseCase, GetCurrentSettingsUseCase getCurrentSettingsUseCase, ChangeSettingsUseCase changeSettingsUseCase) {
        this.context = context;
        this.getTasksByUserUseCase = getTasksByUserUseCase;
        this.signOutUseCase = signOutUseCase;
        this.getAllTasksUseCase = getAllTasksUseCase;
        this.getCurrentSettingsUseCase = getCurrentSettingsUseCase;
        this.changeSettingsUseCase = changeSettingsUseCase;
        getSettings();
        getTasks();
    }

    public LiveData<ArrayList<MainListItems>> getItemsLiveData() {
        return itemsLiveData;
    }

    public LiveData<Boolean> getIsMyTasks() {
        return isMyTasks;
    }

    private void getSettings(){
        isMyTasks.setValue(getCurrentSettingsUseCase.execute());
        if (isMyTasks.getValue() == null) {
            isMyTasks.setValue(true);
        }
    }

    public void setSettings(){
        boolean settings = !isMyTasks.getValue();
        isMyTasks.setValue(settings);
        changeSettingsUseCase.execute(settings);
        getTasks();
    }

    private void getTasks(){
        if(isMyTasks.getValue()){
            getTasksByUserUseCase.execute(this);
        }
        else {
            getAllTasksUseCase.execute(this);
        }
    }

    public void signOut(){
        signOutUseCase.execute();
    }

    @Override
    public void onGetTaskDomainResponse(TaskDomainResponseModel response) {
        if(response.isError()){
            Toast.makeText(context, "Tasks downloading is failed", Toast.LENGTH_SHORT).show();
        }
        else {
            ArrayList<MainListItems> items = new ArrayList<>();
            ArrayList<TaskDomainModel> tasks = response.getTaskDomainModelArrayList();

            for (TaskDomainModel task : tasks) {
                MainListItems item = new MainListItems(task.getOutcomeDocumentContent(), task.getOutcomeDocumentStatus(), task.getTaskID());
                items.add(item);
            }

            itemsLiveData.setValue(items);
        }
    }
}
