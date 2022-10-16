package com.example.taskmanager.di;

import android.content.Context;

import com.example.data.repository.SettingsRepository;
import com.example.data.repository.TaskRepository;
import com.example.data.repository.UserRepository;
import com.example.data.storage.interfaces.storageIntrfaces.SettingsStorageInterface;
import com.example.data.storage.interfaces.storageIntrfaces.TaskStorageInterface;
import com.example.data.storage.interfaces.storageIntrfaces.UserStorageInterface;
import com.example.data.storage.storages.SettingsStorage;
import com.example.data.storage.storages.TasksStorage;
import com.example.data.storage.storages.UserStorage;
import com.example.domain.Interfaces.SettingsRepositoryInterface;
import com.example.domain.Interfaces.TaskRepositoryInterface;
import com.example.domain.Interfaces.UserRepositoryInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModules {

    @Provides
    @Singleton
    public UserRepositoryInterface provideUserRepositoryInterface(UserStorageInterface userStorageInterface){
        return new UserRepository(userStorageInterface);
    }

    @Provides
    @Singleton
    public TaskRepositoryInterface provideTaskRepositoryInterface(TaskStorageInterface taskStorageInterface){
        return new TaskRepository(taskStorageInterface);
    }

    @Provides
    @Singleton
    public SettingsRepositoryInterface provideSettingsRepositoryInterface(SettingsStorageInterface settingsStorageInterface){
        return new SettingsRepository(settingsStorageInterface);
    }

    @Provides
    @Singleton
    public UserStorageInterface provideUserStorageInterface(){
        return new UserStorage();
    }

    @Provides
    @Singleton
    public TaskStorageInterface provideTaskStorageInterface(){
        return new TasksStorage();
    }

    @Provides
    @Singleton
    public SettingsStorageInterface provideSettingsStorageInterface(@ApplicationContext Context context){
        return new SettingsStorage(context);
    }

}
