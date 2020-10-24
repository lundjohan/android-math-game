package com.johanlund.mathgame.level;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import static com.johanlund.mathgame.common.Constants.NR_OF_LEVEL;

public class OneLevelFragment extends Fragment implements AnswerQuestionFragment.Listener {
    private OneLevelViewMvc viewMvc;
    private OneLevelFragmentListener callback;

    /*ref to QuestionAdapter could also be placed in view, but feels more naturally here as this is
    the controller.*/
    QuestionAdapter questionsAdapter;
    private int nrOfTotalQuestions;
    private int correctAnswers = 0;
    private int levelNr;
    private int startTimeMilliSec;

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
            levelNr = args.getInt(NR_OF_LEVEL);
            viewMvc.bindLevelTitleToView("LEVEL "+ levelNr);

            //Score
            nrOfTotalQuestions = qms.size();
            viewMvc.bindScoreToView(doScoreStr());

            //Time
            startTimeMilliSec = level.getTimeInSecPerQuestion() * nrOfTotalQuestions * 1000;

        }
        new CountDownTimer(startTimeMilliSec, 1000) {

            public void onTick(long millisUntilFinished) {
                viewMvc.bindTimeToView("" +millisUntilFinished / 1000);
            }

            public void onFinish() {
                //create a Dialog with "You move down one level" And an OK btn.
            }
        }.start();
        return viewMvc.getRootView();
    }

    @Override
    public void answerIsCorrect(String questionModel) {
        ++correctAnswers;
        viewMvc.bindScoreToView(doScoreStr());
        questionsAdapter.popQuestion(questionModel);
        if (correctAnswers == nrOfTotalQuestions){
           callback.changeLevelTo(levelNr + 1);
        }
    }

    private String doScoreStr() {
        return "" + correctAnswers + "/ " + nrOfTotalQuestions;
    }
}

