package com.johanlund.mathgame.screens.questionanswer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.johanlund.mathgame.common.models.QuestionModel;
import com.johanlund.mathgame.debug.BackStackLogger;

import static com.johanlund.mathgame.common.Constants.QUESTION_MODEL;

/**
 * A Fragment that shows one question (e.g. "3+4"),
 * allows him to answer, and checks if it is correct
 */
public class AnswerQuestionFragment extends Fragment implements AnswerQuestionViewMvc.Listener {
    private AnswerQuestionViewMvc viewMvc;
    private Listener callback;
    String TAG = this.getClass().getName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = (Listener) getParentFragment();

        /* Without this line we go back to former AnswerQuestionFragment inside ViewPager on backpress.
        * Here we make the Fragment Parent of AnswerQuestionFragment handle the back press.*/
        //requireActivity().getOnBackPressedDispatcher().addCallback(this, callback.handleBackPress());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        QuestionModel qm = (QuestionModel) args.getParcelable(QUESTION_MODEL);
        viewMvc = new AnswerQuestionViewMvcImpl(inflater, container);
        viewMvc.bindQuestionToView(qm);

        /**
         * This always log backstack count == 0.
         * This class resides inside a ViewPager2, which apparently doesn't use a backstack.
         */
        new BackStackLogger(this.getClass().getName(),getParentFragmentManager()).log();
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

    public interface Listener {
        void answerIsCorrect(String id);
       // OnBackPressedCallback handleBackPress();
    }
}