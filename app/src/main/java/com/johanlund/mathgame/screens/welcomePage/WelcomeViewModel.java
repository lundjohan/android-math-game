package com.johanlund.mathgame.screens.welcomePage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.johanlund.mathgame.common.models.LevelInfo;
import com.johanlund.mathgame.questionsProducer.DaggerQuestionsProducerFactory;
import com.johanlund.mathgame.questionsProducer.QuestionsProducer;

public class WelcomeViewModel extends ViewModel {
    private LevelInfo[] infoAboutLevels;
    public WelcomeViewModel(){
        QuestionsProducer qp = DaggerQuestionsProducerFactory.create().questionsProducer();
        infoAboutLevels = qp.getLevelInfos();
    }


    private MutableLiveData<String> chosenLevel = new MutableLiveData<>();
    public LiveData<String> getChosenLevel() {
        if (chosenLevel.getValue() == null){
            chosenLevel.setValue("1");
        }
        return chosenLevel;}

    private MutableLiveData<String> description = new MutableLiveData<>();
    public LiveData<String> getLevelInfo(){
        return description;
    }

    private MutableLiveData<String> difficulty = new MutableLiveData<>();
    public LiveData<String> getDifficulty(){
        return difficulty;
    }

    private MutableLiveData<Boolean> isStartPressed = new MutableLiveData<>(false);
    public LiveData<Boolean> isStartGamePressed() {return isStartPressed;}

    public String getNrOfLastLevel(){
        return Integer.toString(infoAboutLevels.length - 1);
    }

    public void onSeekBarChange(int progress){

        chosenLevel.setValue(String.valueOf(progress + 1));
        difficulty.setValue(infoAboutLevels[progress].getDifficulty());
        description.setValue(infoAboutLevels[progress].getDescription());
    }

    public void onStartLevel(){
        isStartPressed.setValue(true);
    }

    public void onStartLevelComplete(){isStartPressed.setValue(false);}




}
