package com.johanlund.mathgame.screens.level;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.johanlund.mathgame.App;
import com.johanlund.mathgame.common.models.Level;
import com.johanlund.mathgame.common.models.QuestionModel;
import com.johanlund.mathgame.questionsProducer.DaggerQuestionsProducerComponent;
import com.johanlund.mathgame.questionsProducer.QuestionsProducer;

import java.util.ArrayList;
import java.util.Arrays;

public class LevelViewModel extends ViewModel {
    private final int QUESTIONS_PER_LEVEL = 8;
    private int TOT_NR_OF_LEVELS;
    private int startingNrOfQuestions;
    private int currentScore = 0;
    private Level level;
    private CountDownTimer timer;
    private QuestionsProducer qp;

    public LevelViewModel(QuestionsProducer qp, int chosenLevel) {
        this.qp = qp;
        this.currentLevel.setValue(chosenLevel);
        initiateNewLevel();
    }

    //called on startup and also on level change
    void initiateNewLevel() {
        //retrieve a level
        level = qp.getLevel(currentLevel.getValue(), QUESTIONS_PER_LEVEL);
        TOT_NR_OF_LEVELS = qp.getTotalNrOfLevels();

        startingNrOfQuestions = level.getQuestions().length;

        //new level => reset score
        currentScore = 0;
        currentScoreStr.setValue(makeScoreStr(currentScore, startingNrOfQuestions));

        //time
        long millisStartTime = level.getTimeInSecPerQuestion() * startingNrOfQuestions * 1000;
        timer = new CountDownTimer(millisStartTime, 1000) {

            public void onTick(long millisUntilFinished) {
                secRemaining.setValue((int) millisUntilFinished / 1000);
            }

            public void onFinish() {
                isTimeFinished.setValue(true);
                currentLevel.setValue(currentLevel.getValue() == 1
                        ? 1
                        : currentLevel.getValue() - 1);
            }
        }.start();
    }

    private MutableLiveData<Integer> currentLevel = new MutableLiveData<>();
    public LiveData<Integer> getCurrentLevel() {
        return currentLevel;
    }

    private MutableLiveData<String> currentScoreStr = new MutableLiveData<>();
    public LiveData<String> getCurrentScore() {
        return currentScoreStr;
    }

    private MutableLiveData<Integer> secRemaining = new MutableLiveData<>();
    public LiveData<Integer> getSecRemaining() {
        return secRemaining;
    }

    private MutableLiveData<Boolean> isTimeFinished = new MutableLiveData<>();
    public LiveData<Boolean> isTimeFinished() {
        return isTimeFinished;
    }

    public void onTimeIsFinishedComplete() {
        isTimeFinished.setValue(false);
    }

    public void increaseScoreByOne() {
        ++currentScore;
        currentScoreStr.setValue(makeScoreStr(currentScore, startingNrOfQuestions));
        if (currentScore == startingNrOfQuestions) {
            timer.cancel();
            //garbage collection:
            //timer = null;
            if (currentLevel.getValue() == TOT_NR_OF_LEVELS) {
                isGameFinished.setValue(true);
            } else {
                currentLevel.setValue(currentLevel.getValue() + 1);
                isLevelCompleted.setValue(true);
            }
        }
    }

    private MutableLiveData<Boolean> isLevelCompleted = new MutableLiveData<>(false);

    public LiveData<Boolean> isLevelCompleted() {
        return isLevelCompleted;
    }

    public void onFinishedLevelComplete() {
        isLevelCompleted.setValue(false);
    }

    private MutableLiveData<Boolean> isGameFinished = new MutableLiveData<>(false);
    ;

    public LiveData<Boolean> isGameFinished() {
        return isGameFinished;
    }

    public void onFinishedGameComplete() {
        isGameFinished.setValue(false);
    }

    public ArrayList<QuestionModel> getQuestionsList() {
        return new ArrayList<>(Arrays.asList(level.getQuestions()));
    }

    private static String makeScoreStr(int userScore, int totScore) {
        return "" + userScore + "/ " + totScore;
    }
}
