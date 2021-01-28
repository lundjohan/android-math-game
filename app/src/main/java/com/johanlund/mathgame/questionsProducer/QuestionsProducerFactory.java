package com.johanlund.mathgame.questionsProducer;

import dagger.Component;

@Component(modules = QuestionsProducerModule.class)
public interface QuestionsProducerFactory {
    QuestionsProducer questionsProducer();
}
