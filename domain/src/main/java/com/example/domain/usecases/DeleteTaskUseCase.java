package com.example.domain.usecases;

import com.example.domain.Interfaces.TaskRepositoryInterface;

public class DeleteTaskUseCase {
    private TaskRepositoryInterface taskRepositoryInterface;

    public DeleteTaskUseCase(TaskRepositoryInterface taskRepositoryInterface) {
        this.taskRepositoryInterface = taskRepositoryInterface;
    }

    public void execute(String taskId){
        taskRepositoryInterface.deleteTask(taskId);
    }
}
