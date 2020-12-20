package com.johanlund.mathgame.screens.level;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LevelViewModelFactory implements ViewModelProvider.Factory {
    int levelNr;

    LevelViewModelFactory(int levelNr) {
        this.levelNr = levelNr;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LevelViewModel.class)) {
            return (T) new LevelViewModel(levelNr);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

