package com.johanlund.mathgame.questionsProducer;

import com.johanlund.mathgame.common.models.Level;
import com.johanlund.mathgame.common.models.LevelInfo;

import java.util.ArrayList;

import javax.inject.Inject;

/*
Simplified class without any calls to repository nor randomization of questions order.
 */

public class FakeQuestionsProducer implements QuestionsProducer {
    static ArrayList<Level> levels;
    static LevelInfo[] levelInfos;

    @Inject
    public FakeQuestionsProducer(){

    }

    public FakeQuestionsProducer(ArrayList<Level> levels, LevelInfo[] levelInfos) {
        this.levels = levels;
        this.levelInfos = levelInfos;
    }

    @Override
    public Level getLevel(int level, int nrOfQuestions) {
        //levels start at 1
        return levels.get(level - 1);
    }

    @Override
    public int getTotalNrOfLevels() {
        return levels.size();
    }

    @Override
    public LevelInfo[] getLevelInfos() {
        return levelInfos;
    }
}
