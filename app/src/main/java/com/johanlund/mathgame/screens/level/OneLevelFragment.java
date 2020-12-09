package com.johanlund.mathgame.screens.level;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.QuestionModel;
import com.johanlund.mathgame.questionsProducer.QuestionsProducer;
import com.johanlund.mathgame.questionsProducer.QuestionsProducerImpl;
import com.johanlund.mathgame.screens.questionanswer.AnswerQuestionFragment;

import java.util.ArrayList;
import java.util.Arrays;

import static com.johanlund.mathgame.common.Constants.NR_OF_LEVEL;
import static com.johanlund.mathgame.common.Constants.TOT_NR_OF_LEVELS;

public class OneLevelFragment extends Fragment implements AnswerQuestionFragment.Listener {
    private final int QUESTIONS_PER_LEVEL = 8;

    private OneLevelViewMvc viewMvc;

    /*ref to QuestionAdapter could also be placed in view, but feels more naturally here as this is
    the controller.*/
    private QuestionAdapter questionsAdapter;
    private int nrOfTotalQuestions;
    private int correctAnswers = 0;
    private int totNrOfLevels;
    private int currentLevel = 1;

    //We need an ArrayList to be able to remove inside List
    private ArrayList<QuestionModel> qms;
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
        viewMvc = new OneLevelViewMvcImpl(inflater, container);

        //ORIENTATION CHANGE
        if (savedInstanceState != null) {

            //Level nr (used for getting level and also for title)
            currentLevel = savedInstanceState.getInt(NR_OF_LEVEL);

            //total nr of levels in database
            totNrOfLevels = savedInstanceState.getInt(TOT_NR_OF_LEVELS);

            //Time
            millisStartTime = savedInstanceState.getLong(MILLIS_BEFORE_TIMES_UP);

            //Questions List
            qms = savedInstanceState.getParcelableArrayList(REMAINING_QUESTIONS);

            //Score
            nrOfTotalQuestions = savedInstanceState.getInt(ORIGINAL_TOTAL_QUESTIONS);
            correctAnswers = nrOfTotalQuestions - qms.size();
        }

        //FIRST INITIATION
        else {
            //Level nr (used for getting level and also for title)
            currentLevel = OneLevelFragmentArgs.fromBundle(getArguments()).getLevelNr();
            initiateLevelPartOne();
        }
        initiateLevelPartTwo();
        return viewMvc.getRootView();
    }


    private void initiateLevel() {
        initiateLevelPartOne();
        initiateLevelPartTwo();

    }

    private void initiateLevelPartOne() {
        //retrieve a level
        QuestionsProducer qp = new QuestionsProducerImpl();
        Level level = qp.getLevel(currentLevel, QUESTIONS_PER_LEVEL);
        totNrOfLevels = qp.getTotalNrOfLevels();

        //Questions List
        qms = new ArrayList<>(Arrays.asList(level.getQuestions()));

        //Score
        nrOfTotalQuestions = qms.size();

        //Time
        millisStartTime = level.getTimeInSecPerQuestion() * nrOfTotalQuestions * 1000;
        millisBeforeFinished = millisStartTime;
    }

    private void initiateLevelPartTwo() {
        questionsAdapter = new QuestionAdapter(this, qms);
        viewMvc.createViewPager2(questionsAdapter);

        viewMvc.bindLevelTitleToView("LEVEL " + currentLevel);
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
                    levelTimeIsUp();
                }
            }
        }.start();
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        state.putInt(NR_OF_LEVEL, currentLevel);
        state.putInt(TOT_NR_OF_LEVELS, totNrOfLevels);
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
            levelCompleted();
        }
    }

    private void levelCompleted() {
        if (currentLevel == totNrOfLevels) {
            int action = OneLevelFragmentDirections.actionLevelToWin().getActionId();
            NavHostFragment.findNavController(this).navigate(action);
            return;
        }
        ++currentLevel;
        new OkDialog().startDialog(this, R.string.well_done, R.string.moving_up);

    }

    public void levelTimeIsUp() {
        //If possible: move down one level
        currentLevel = currentLevel > 1 ? currentLevel - 1 : 1;
        new OkDialog().startDialog(this, R.string.time_is_up, R.string.moving_down);
    }

    private String doScoreStr() {
        return "" + correctAnswers + "/ " + nrOfTotalQuestions;
    }

    //INNER CLASS handling Dialogs that will change level up or down
    private class OkDialog {
        private void startDialog(Fragment fragment, int title, int msg) {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
            builder.setPositiveButton(R.string.ok, (dialog, id) -> {
                        OneLevelFragmentDirections.ActionLevelToLevel action = OneLevelFragmentDirections.actionLevelToLevel();
                        action.setLevelNr(currentLevel);
                        NavHostFragment.findNavController(fragment).navigate(action);
                    }
            );
            final AlertDialog dialog = builder.setMessage(msg)
                    .setTitle(title)
                    .create();
            dialog.show();

            //Put OK btn in the centre
            final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
            positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
            positiveButton.setLayoutParams(positiveButtonLL);
        }
    }
}

