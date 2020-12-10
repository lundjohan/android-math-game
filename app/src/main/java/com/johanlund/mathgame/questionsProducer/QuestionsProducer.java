package com.johanlund.mathgame.questionsProducer;

import com.johanlund.mathgame.common.models.Level;
import com.johanlund.mathgame.common.models.LevelInfo;

public interface QuestionsProducer {
    Level getLevel(int level, int nrOfQuestions);

    int getTotalNrOfLevels();

    LevelInfo[] getLevelInfos();
}
