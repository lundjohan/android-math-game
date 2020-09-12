package com.johanlund.mathgame.questionanswer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AnswerQuestionFragment extends Fragment implements AnswerQuestionViewMvc.Listener{
    private AnswerQuestionViewMvc viewMvc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewMvc = new AnswerQuestionViewMvcImpl(inflater, container);
        //model will be created higher in hierarchy in future, this is temp fix
        QuestionModel q = new QuestionModel(4,6,'+');
        viewMvc.bindQuestionToView(q);
        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewMvc.setListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener();
    }

    @Override
    public void checkAnswer() {
        QuestionModel q = viewMvc.retrieveQuestionFromView();
        Integer answer = viewMvc.retrieveAnswer();
        if (answer == null){
            return;
        }
        Integer realAnswer = null;
        switch (q.getOperator()){
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

        if (realAnswer.intValue() == answer){
            viewMvc.doCorrectGraphics();
        }
        else {
            viewMvc.doIncorrectGraphics();
        }
        }
}