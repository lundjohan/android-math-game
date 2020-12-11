package com.johanlund.mathgame.screens.win;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WinViewModel extends ViewModel {
    private MutableLiveData<Boolean> gameIsFinished = new MutableLiveData<>();
    public LiveData<Boolean>isGameFinished(){
        return gameIsFinished;
    }

    public void onRestartGame(){
        gameIsFinished.setValue(true);
    }
}
