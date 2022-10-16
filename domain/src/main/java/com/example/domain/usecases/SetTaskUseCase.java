package com.example.domain.usecases;

import com.example.domain.Interfaces.TaskRepositoryInterface;
import com.example.domain.models.TaskDomainModel;

public class SetTaskUseCase {
    private TaskRepositoryInterface taskRepositoryInterface;

    public SetTaskUseCase(TaskRepositoryInterface taskRepositoryInterface) {
        this.taskRepositoryInterface = taskRepositoryInterface;
    }

    public void execute(TaskDomainModel task){
        taskRepositoryInterface.saveTask(task);
    }
}
