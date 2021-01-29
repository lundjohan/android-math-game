package com.johanlund.mathgame.screens.level;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.johanlund.mathgame.questionsProducer.QuestionsProducer;

public class LevelViewModelFactory implements ViewModelProvider.Factory {
    private int levelNr;
    private QuestionsProducer qp;
    LevelViewModelFactory(QuestionsProducer questionProducer, int levelNr) {
        this.qp = questionProducer;
        this.levelNr = levelNr;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LevelViewModel.class)) {
            return (T) new LevelViewModel(qp,levelNr);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

