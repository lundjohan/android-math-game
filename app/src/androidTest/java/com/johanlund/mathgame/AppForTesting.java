package com.johanlund.mathgame;

import com.johanlund.mathgame.questionsProducer.DaggerFakeQuestionsProducerComponent;
import com.johanlund.mathgame.questionsProducer.QuestionsProducer;

public class AppForTesting extends App{
    @Override
    public QuestionsProducer initiateComponent(){
        return DaggerFakeQuestionsProducerComponent.create().questionsProducer();
    }
}
