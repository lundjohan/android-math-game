package com.johanlund.mathgame.questionsProducer;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class FakeQuestionsProducerModule {
    @Binds
    public abstract QuestionsProducer questionsProducer(FakeQuestionsProducer qp);

}
