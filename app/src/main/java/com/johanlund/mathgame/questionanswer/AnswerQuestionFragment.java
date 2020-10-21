package com.johanlund.mathgame.questionanswer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.johanlund.mathgame.common.QuestionModel;

import static com.johanlund.mathgame.common.Constants.QUESTION_MODEL;

public class AnswerQuestionFragment extends Fragment implements AnswerQuestionViewMvc.Listener {
    private AnswerQuestionViewMvc viewMvc;
    private Listener callback;
    String TAG = this.getClass().getName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = (Listener) getParentFragment();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        QuestionModel qm = (QuestionModel) args.getSerializable(QUESTION_MODEL);
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

            callback.answerIsCorrect(viewMvc.retrieveQuestionFromView().toString());
        } else {
            viewMvc.doIncorrectGraphics();
        }
    }
    public interface Listener  {
        void answerIsCorrect(String id);
    }
}