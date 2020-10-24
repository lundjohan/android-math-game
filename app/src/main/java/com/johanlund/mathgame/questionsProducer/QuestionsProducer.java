package com.johanlund.mathgame.questionsProducer;

import com.johanlund.mathgame.common.Level;

public interface QuestionsProducer {
    Level retrieveLevel(int level, int nrOfQuestions);

    int getTotalNrOfLevels();
}
