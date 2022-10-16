package com.example.data.storage.storages;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.data.storage.interfaces.storageIntrfaces.SettingsStorageInterface;
import com.example.data.utils.Constants;

public class SettingsStorage implements SettingsStorageInterface {
    private Context context;
    private SharedPreferences sharedPreferences;

    public SettingsStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public boolean getCurrentSettings() {
        return sharedPreferences.getBoolean(Constants.SETTING, true);
    }

    @Override
    public void setSettings(boolean isMyTasks) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.SETTING, isMyTasks);
        editor.apply();
    }
}
