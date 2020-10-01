package com.johanlund.mathgame.questionsProducer;

import com.johanlund.mathgame.common.Level;

public interface QuestionsProducer {
    public Level retrieveLevel(int level, int nrOfQuestions);
}
