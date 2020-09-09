package com.johanlund.mathgame.questionanswer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johanlund.mathgame.R;

public class AnswerQuestionViewMvcImpl implements AnswerQuestionViewMvc{
    private final View view;

    public AnswerQuestionViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        this.view = inflater.inflate(R.layout.fragment_question_answer, container, false);
    }

    @Override
    public void bindQuestionToView(QuestionModel qm) {
        TextView question = view.findViewById(R.id.mathQuestion);
        question.setText(qm.getLeft() + " " + qm.getOperator() + " " + qm.getRight() + " =");
    }

    @Override
    public QuestionModel retrieveQuestionFromView() {
        //create and return QuestionModel obj from UI

        return null;
    }

    @Override
    public View getRootView() {
        return view;
    }
}
