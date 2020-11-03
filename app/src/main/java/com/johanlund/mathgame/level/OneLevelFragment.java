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

import static com.johanlund.mathgame.common.Constants.LEVEL;
import static com.johanlund.mathgame.common.Constants.NR_OF_LEVEL;

public class OneLevelFragment extends Fragment implements AnswerQuestionFragment.Listener {
    private OneLevelViewMvc viewMvc;
    private OneLevelFragment.Listener callback;

    /*ref to QuestionAdapter could also be placed in view, but feels more naturally here as this is
    the controller.*/
    private QuestionAdapter questionsAdapter;
    private int nrOfTotalQuestions;
    private int correctAnswers = 0;
    private int levelNr;
    private long millisStartTime;
    private long millisBeforeFinished;
    private CountDownTimer countDownTimer;

    private final String ORIGINAL_TOTAL_QUESTIONS = "totalQuestions";
    private final String MILLIS_BEFORE_TIMES_UP = "millisTimesUp";
    private final String REMAINING_QUESTIONS = "remainingQuestions";
    private final String TAG = this.getClass().getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callback = (OneLevelFragment.Listener) getActivity();
        viewMvc = new OneLevelViewMvcImpl(inflater, container);
        Bundle args = getArguments();

        //We need an ArrayList to be able to remove inside List
        ArrayList<QuestionModel> qms;

        //ORIENTATION CHANGE
        if (savedInstanceState != null) {

            //Time
            millisStartTime = savedInstanceState.getLong(MILLIS_BEFORE_TIMES_UP);

            //Questions List
            qms = savedInstanceState.getParcelableArrayList(REMAINING_QUESTIONS);

            //Score
            nrOfTotalQuestions = savedInstanceState.getInt(ORIGINAL_TOTAL_QUESTIONS);
            correctAnswers = nrOfTotalQuestions - qms.size();

            //Level title
            levelNr = savedInstanceState.getInt(NR_OF_LEVEL);
        }

        //FIRST INITIATION
        else {
            //Level
            Level level = (Level) args.getParcelable(LEVEL);

            //Questions List
            qms = new ArrayList<>(Arrays.asList(level.getQuestions()));

            //Score
            nrOfTotalQuestions = qms.size();

            //Time
            millisStartTime = level.getTimeInSecPerQuestion() * nrOfTotalQuestions * 1000;
            millisBeforeFinished = millisStartTime;

            //Level title
            levelNr = args.getInt(NR_OF_LEVEL);
        }

        questionsAdapter = new QuestionAdapter(this, qms);
        viewMvc.createViewPager2(questionsAdapter);

        viewMvc.bindLevelTitleToView("LEVEL " + levelNr);
        viewMvc.bindScoreToView(doScoreStr());

        final Fragment here = this;
        countDownTimer = new CountDownTimer(millisStartTime, 1000) {

            public void onTick(long millisUntilFinished) {
                millisBeforeFinished = millisUntilFinished;
                viewMvc.bindTimeToView("" + millisUntilFinished / 1000);
            }

            public void onFinish() {

                //This check  is needed so no threads from old/ other Fragments are calling callback.
                if (here.isVisible()) {
                    callback.levelTimeIsUp();
                }
            }
        }.start();

        return viewMvc.getRootView();
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        state.putInt(NR_OF_LEVEL, levelNr);
        state.putInt(ORIGINAL_TOTAL_QUESTIONS, nrOfTotalQuestions);
        state.putLong(MILLIS_BEFORE_TIMES_UP, millisBeforeFinished);
        state.putParcelableArrayList(REMAINING_QUESTIONS, questionsAdapter.getQuestionModels());
    }

    @Override
    public void answerIsCorrect(String questionModel) {
        ++correctAnswers;
        viewMvc.bindScoreToView(doScoreStr());
        questionsAdapter.popQuestion(questionModel);
        if (correctAnswers == nrOfTotalQuestions) {

            //Cancel the countdown (otherwise it keeps on in the background before user pressed ok)
            countDownTimer.cancel();
            callback.levelCompleted();
        }
    }

    private String doScoreStr() {
        return "" + correctAnswers + "/ " + nrOfTotalQuestions;
    }

    public interface Listener {
        void levelCompleted();

        //The countdown time for level has reached zero
        void levelTimeIsUp();
    }
}

