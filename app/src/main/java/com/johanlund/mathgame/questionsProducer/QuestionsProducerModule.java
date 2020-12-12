package com.johanlund.mathgame.questionsProducer;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class QuestionsProducerModule {
    @Binds
    public abstract QuestionsProducer questionProducerImpl(QuestionsProducerImpl impl);
}
