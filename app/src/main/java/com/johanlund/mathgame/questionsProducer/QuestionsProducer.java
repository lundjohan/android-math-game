package com.johanlund.mathgame.questionsProducer;

import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.LevelInfo;

public interface QuestionsProducer {
    Level getLevel(int level, int nrOfQuestions);

    int getTotalNrOfLevels();

    LevelInfo[] getLevelInfos();
}
