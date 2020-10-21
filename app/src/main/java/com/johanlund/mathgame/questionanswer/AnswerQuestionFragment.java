package com.johanlund.mathgame.questionanswer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.johanlund.mathgame.common.QuestionModel;

import static com.johanlund.mathgame.common.Constants.POSITION_IN_ADAPTER;
import static com.johanlund.mathgame.common.Constants.QUESTION_MODEL;

public class AnswerQuestionFragment extends Fragment implements AnswerQuestionViewMvc.Listener {
    private AnswerQuestionViewMvc viewMvc;
    private Listener callback;
    QuestionModel qm;
    String TAG = this.getClass().getName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = (Listener) getParentFragment();
        Bundle args = getArguments();
        qm = (QuestionModel) args.getSerializable(QUESTION_MODEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        viewMvc = new AnswerQuestionViewMvcImpl(inflater, container);
        viewMvc.bindQuestionToView(qm);
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
        if (answer == null) {
            return;
        }
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
            viewMvc.doCorrectGraphics();

            callback.answerIsCorrect(qm.hashCode());
        } else {
            viewMvc.doIncorrectGraphics();
        }
    }
    public interface Listener  {
        void answerIsCorrect(int id);
    }
}