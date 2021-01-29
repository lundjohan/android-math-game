package com.johanlund.mathgame.screens.welcomePage;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.johanlund.mathgame.common.models.LevelInfo;

public class WelcomeViewModelFactory implements ViewModelProvider.Factory{
    private LevelInfo[] infos;
    public WelcomeViewModelFactory(LevelInfo[] infos) {
        this.infos = infos;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WelcomeViewModel.class)) {
            return (T) new WelcomeViewModel(infos);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
