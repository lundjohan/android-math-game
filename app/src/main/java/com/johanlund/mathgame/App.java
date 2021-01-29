package com.johanlund.mathgame;

import android.app.Application;

import com.johanlund.mathgame.common.di.AssetManagerModule;
import com.johanlund.mathgame.common.di.DaggerAssetsComponent;
import com.johanlund.mathgame.questionsProducer.DaggerQuestionsProducerComponent;
import com.johanlund.mathgame.questionsProducer.QuestionsProducer;

public class App extends Application {
    public static QuestionsProducer qp;
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAssetsComponent.builder().
                assetManagerModule(new AssetManagerModule(getApplicationContext().
                        getAssets())).build();
        qp = initiateComponent();
    }
    public QuestionsProducer initiateComponent(){
        return DaggerQuestionsProducerComponent.create().questionsProducer();
    }
    public QuestionsProducer getQuestionProducer(){
        return qp;
    }
}
