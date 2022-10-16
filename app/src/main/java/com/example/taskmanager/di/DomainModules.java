package com.example.taskmanager.di;

import com.example.domain.Interfaces.SettingsRepositoryInterface;
import com.example.domain.Interfaces.TaskRepositoryInterface;
import com.example.domain.Interfaces.UserRepositoryInterface;
import com.example.domain.usecases.ChangeSettingsUseCase;
import com.example.domain.usecases.DeleteTaskUseCase;
import com.example.domain.usecases.GetAllTasksUseCase;
import com.example.domain.usecases.GetCurrentSettingsUseCase;
import com.example.domain.usecases.GetTaskUseCase;
import com.example.domain.usecases.GetTasksByUserUseCase;
import com.example.domain.usecases.GetUserProfileUseCase;
import com.example.domain.usecases.IsCurrentUserExistUseCase;
import com.example.domain.usecases.IsMyTaskUseCase;
import com.example.domain.usecases.SetTaskUseCase;
import com.example.domain.usecases.SignInByEmailUseCase;
import com.example.domain.usecases.SignOutUseCase;
import com.example.domain.usecases.SignUpByEmailUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public class DomainModules {

    @Provides
    public IsCurrentUserExistUseCase provideIsCurrentUserExistUseCase(UserRepositoryInterface userRepositoryInterface){
        return new IsCurrentUserExistUseCase(userRepositoryInterface);
    }

    @Provides
    public SignInByEmailUseCase provideSignInByEmailUseCase(UserRepositoryInterface userRepositoryInterface){
        return new SignInByEmailUseCase(userRepositoryInterface);
    }

    @Provides
    public SignUpByEmailUseCase provideSignUpByEmailUseCase(UserRepositoryInterface userRepositoryInterface){
        return new SignUpByEmailUseCase(userRepositoryInterface);
    }

    @Provides
    public SignOutUseCase provideSignOutUseCase(UserRepositoryInterface userRepositoryInterface){
        return new SignOutUseCase(userRepositoryInterface);
    }

    @Provides
    public GetUserProfileUseCase provideGetUserProfileUseCase(UserRepositoryInterface userRepositoryInterface){
        return new GetUserProfileUseCase(userRepositoryInterface);
    }

    @Provides
    public IsMyTaskUseCase provideIsMyTaskUseCase(UserRepositoryInterface userRepositoryInterface){
        return new IsMyTaskUseCase(userRepositoryInterface);
    }

    @Provides
    public SetTaskUseCase provideSetTaskUseCase(TaskRepositoryInterface taskRepositoryInterface){
        return new SetTaskUseCase(taskRepositoryInterface);
    }

    @Provides
    public GetTasksByUserUseCase provideGetTasksByUserUseCase(TaskRepositoryInterface taskRepositoryInterface){
        return new GetTasksByUserUseCase(taskRepositoryInterface);
    }

    @Provides
    public GetAllTasksUseCase provideGetAllTasksUseCase(TaskRepositoryInterface taskRepositoryInterface){
        return new GetAllTasksUseCase(taskRepositoryInterface);
    }

    @Provides
    public GetTaskUseCase provideGetTaskUseCase(TaskRepositoryInterface taskRepositoryInterface){
        return new GetTaskUseCase(taskRepositoryInterface);
    }

    @Provides
    public DeleteTaskUseCase provideDeleteTaskUseCase(TaskRepositoryInterface taskRepositoryInterface){
        return new DeleteTaskUseCase(taskRepositoryInterface);
    }

    @Provides
    public GetCurrentSettingsUseCase provideGetCurrentSettingsUseCase(SettingsRepositoryInterface settingsRepositoryInterface){
        return new GetCurrentSettingsUseCase(settingsRepositoryInterface);
    }

    @Provides
    public ChangeSettingsUseCase provideChangeSettingsUseCase(SettingsRepositoryInterface settingsRepositoryInterface){
        return new ChangeSettingsUseCase(settingsRepositoryInterface);
    }
}
