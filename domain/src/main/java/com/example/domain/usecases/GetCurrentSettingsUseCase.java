package com.example.domain.usecases;

import com.example.domain.Interfaces.SettingsRepositoryInterface;

public class GetCurrentSettingsUseCase {
    private SettingsRepositoryInterface settingsRepositoryInterface;

    public GetCurrentSettingsUseCase(SettingsRepositoryInterface settingsRepositoryInterface) {
        this.settingsRepositoryInterface = settingsRepositoryInterface;
    }

    public boolean execute(){
        return settingsRepositoryInterface.getCurrentSettings();
    }
}
