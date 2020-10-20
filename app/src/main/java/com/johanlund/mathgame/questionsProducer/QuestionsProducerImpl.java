package com.johanlund.mathgame.questionsProducer;

import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.QuestionModel;
import com.johanlund.mathgame.questionsDatabase.DaggerLevelDatabaseFactory;
import com.johanlund.mathgame.questionsDatabase.Database;

import java.util.Arrays;

public class QuestionsProducerImpl implements QuestionsProducer {
    /**
     * level >= 1
     */
    @Override
    public Level retrieveLevel(int level, int nrOfQuestions) {
        Database database = DaggerLevelDatabaseFactory.create().database();
        Level tmp = database.getLevel(level);

        //reduce amount of questions in Level
        int notHigherThanThis = tmp.getQuestions().length <
                nrOfQuestions ? tmp.getQuestions().length : nrOfQuestions;
        QuestionModel[] questions = Arrays.copyOfRange(tmp.getQuestions(), 0, notHigherThanThis);
        return new Level(questions, tmp.getTimeInSec());
    }
}
