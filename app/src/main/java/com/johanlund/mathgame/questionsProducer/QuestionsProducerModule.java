package com.johanlund.mathgame.questionsProducer;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class QuestionsProducerModule {
    @Binds
    public abstract QuestionsProducer questionProducer(QuestionsProducerImpl impl);
}
