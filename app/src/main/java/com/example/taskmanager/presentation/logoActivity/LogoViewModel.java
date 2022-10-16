package com.example.taskmanager.presentation.logoActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.domain.usecases.IsCurrentUserExistUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LogoViewModel extends ViewModel {
    private IsCurrentUserExistUseCase isCurrentUserExistUseCase;
    private MutableLiveData<Boolean> isCurrentUserExist = new MutableLiveData<>();

    @Inject
    public LogoViewModel(IsCurrentUserExistUseCase isCurrentUserExistUseCase) {
        this.isCurrentUserExistUseCase = isCurrentUserExistUseCase;
        isCurrentUserExist.setValue(isCurrentUserExistUseCase.execute());
    }

    public LiveData<Boolean> getIsCurrentUserExist() {
        return isCurrentUserExist;
    }
}
