package com.johanlund.mathgame.level;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.QuestionModel;
import com.johanlund.mathgame.questionanswer.AnswerQuestionFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.johanlund.mathgame.common.Constants.LEVEL;
import static com.johanlund.mathgame.common.Constants.LEVEL_TITLE;

public class OneLevelFragment extends Fragment implements AnswerQuestionFragment.Listener {
    private OneLevelViewMvc viewMvc;
    private OneLevelFragmentListener callback;

    /*ref to QuestionAdapter could also be placed in view, but feels more naturally here as this is
    the controller.*/
    QuestionAdapter questionsAdapter;
    private int nrOfTotalQuestions;
    private int correctAnswers = 0;
    private int levelNr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callback = (OneLevelFragmentListener) getActivity();
        viewMvc = new OneLevelViewMvcImpl(inflater, container);

        Bundle args = getArguments();
        if (args != null) {
            //Level
            Level level = (Level) args.getSerializable(LEVEL);

            //We need an ArrayList to be able to remove inside List
            List<QuestionModel> qms  =
                    new ArrayList<QuestionModel>(Arrays.asList(level.getQuestions()));

            questionsAdapter = new QuestionAdapter(this, qms);
            viewMvc.createViewPager2(questionsAdapter);

            //Level title
            String str = args.getString(LEVEL_TITLE);
            viewMvc.bindLevelTitleToView(str);

            //Score
            nrOfTotalQuestions = qms.size();
            viewMvc.bindScoreToView(doScoreStr());

        }
        return viewMvc.getRootView();
    }

    @Override
    public void answerIsCorrect(int questionModelHashcode) {
        ++correctAnswers;
        viewMvc.bindScoreToView(doScoreStr());
        if (correctAnswers == nrOfTotalQuestions){
           // callback.changeLevelTo();
        }
        questionsAdapter.popQuestion(questionModelHashcode);
    }

    private String doScoreStr() {
        return "" + correctAnswers + "/ " + nrOfTotalQuestions;
    }
}

