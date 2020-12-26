package com.johanlund.mathgame.screens.answerquestion;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.johanlund.mathgame.common.models.QuestionModel;

public class AnswerQuestionViewModel extends ViewModel {
    private QuestionModel q;

    public AnswerQuestionViewModel(QuestionModel qm) {
        this.q = qm;
    }

    QuestionModel getQuestion() {
        return q;
    }

    public void checkAnswer(int answer) {
        Integer realAnswer = null;
        switch (q.getOperator()) {
            case '+':
                realAnswer = q.getLeft() + q.getRight();
                break;
            case '-':
                realAnswer = q.getLeft() - q.getRight();
                break;
            case '*':
                realAnswer = q.getLeft() * q.getRight();
                break;
            case '/':
                realAnswer = q.getLeft() / q.getRight();
                break;

        }
        if (realAnswer.intValue() == answer) {
            answerIsCorrect.setValue(true);
        } else {
            answerIsIncorrect.setValue(true);
        }
    }

    private MutableLiveData<Boolean> answerIsCorrect = new MutableLiveData<>();

    public LiveData<Boolean> isAnswerCorrect() {
        return answerIsCorrect;
    }

    private MutableLiveData<Boolean> answerIsIncorrect = new MutableLiveData<>();

    public LiveData<Boolean> isAnswerIncorrect() {
        return answerIsIncorrect;
    }
}
