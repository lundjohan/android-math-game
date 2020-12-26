package com.johanlund.mathgame.screens.answerquestion;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.johanlund.mathgame.common.models.QuestionModel;

public class AnswerQuestionViewModelFactory implements ViewModelProvider.Factory {
    private QuestionModel qm;

    public AnswerQuestionViewModelFactory(QuestionModel qm) {
        this.qm = qm;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AnswerQuestionViewModel.class)) {
            return (T) new AnswerQuestionViewModel(qm);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
