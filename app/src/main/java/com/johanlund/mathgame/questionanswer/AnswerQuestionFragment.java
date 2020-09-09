package com.johanlund.mathgame.questionanswer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class AnswerQuestionFragment extends Fragment {
    private AnswerQuestionViewMvc viewMvc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewMvc = new AnswerQuestionViewMvcImpl(inflater, container);

        //model will be created higher in hierarchy in future, this is temp fix
        QuestionModel q = new QuestionModel(4,6,'+');
        viewMvc.bindQuestionToView(q);
        return viewMvc.getRootView();
    }
}