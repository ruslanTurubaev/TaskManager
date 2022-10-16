package com.example.data.repository;

import com.example.data.storage.interfaces.storageIntrfaces.SettingsStorageInterface;
import com.example.domain.Interfaces.SettingsRepositoryInterface;

public class SettingsRepository implements SettingsRepositoryInterface {
    private SettingsStorageInterface settingsStorageInterface;

    public SettingsRepository(SettingsStorageInterface settingsStorageInterface) {
        this.settingsStorageInterface = settingsStorageInterface;
    }

    @Override
    public boolean getCurrentSettings() {
        return settingsStorageInterface.getCurrentSettings();
    }

    @Override
    public void setSettings(boolean isMyTasks) {
        settingsStorageInterface.setSettings(isMyTasks);
    }
}
