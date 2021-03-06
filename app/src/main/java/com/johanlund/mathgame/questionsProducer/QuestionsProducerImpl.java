package com.johanlund.mathgame.questionsProducer;

import com.johanlund.mathgame.common.models.Level;
import com.johanlund.mathgame.common.models.LevelInfo;
import com.johanlund.mathgame.common.models.QuestionModel;
import com.johanlund.mathgame.questionsDatabase.DaggerLevelDatabaseFactory;
import com.johanlund.mathgame.questionsDatabase.Database;

import java.util.Arrays;
import java.util.Collections;

import javax.inject.Inject;

public class QuestionsProducerImpl implements QuestionsProducer {

    @Inject
    public QuestionsProducerImpl(){}
    /**
     * @param level must be >= 1
     * @return Level if it exists, otwerwise null.
     */
    @Override
    public Level getLevel(int level, int nrOfQuestions) {
        Database database = DaggerLevelDatabaseFactory.create().database();
        Level tmp = database.getLevel(level);
        if (tmp == null) {
            return null;
        }
        QuestionModel[] questions = tmp.getQuestions();

        //randomize
        Collections.shuffle(Arrays.asList(questions));

        //reduce
        int notMoreThanThis = nrOfQuestions < tmp.getQuestions().length ?
                nrOfQuestions : tmp.getQuestions().length;
        questions = Arrays.copyOfRange(questions, 0, notMoreThanThis);

        return new Level(questions, tmp.getTimeInSecPerQuestion());
    }

    @Override
    public int getTotalNrOfLevels() {
        Database database = DaggerLevelDatabaseFactory.create().database();
        int sizeOfLevels = database.getNrOfLevels();
        return sizeOfLevels;
    }

    @Override
    public LevelInfo[] getLevelInfos() {
        Database database = DaggerLevelDatabaseFactory.create().database();
        LevelInfo[] levelInfos = database.getLevelInfos();
        return levelInfos;
    }
}
