package com.johanlund.mathgame.questionsProducer;

import dagger.Component;

@Component(modules = FakeQuestionsProducerModule.class)
public interface FakeQuestionsProducerComponent extends QuestionsProducerComponent{
    QuestionsProducer questionsProducer();
}
