package com.example.domain.usecases;

import com.example.domain.Interfaces.SettingsRepositoryInterface;

public class ChangeSettingsUseCase {
    private SettingsRepositoryInterface settingsRepositoryInterface;

    public ChangeSettingsUseCase(SettingsRepositoryInterface settingsRepositoryInterface) {
        this.settingsRepositoryInterface = settingsRepositoryInterface;
    }

    public void execute(boolean isMyTasks){
        settingsRepositoryInterface.setSettings(isMyTasks);
    }
}
