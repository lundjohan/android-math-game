package com.johanlund.mathgame.questionsProducer;

import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.QuestionModel;
import com.johanlund.mathgame.questionsDatabase.DaggerLevelDatabaseFactory;
import com.johanlund.mathgame.questionsDatabase.Database;

import java.util.Arrays;

public class QuestionsProducerImpl implements QuestionsProducer {
    /**
     * @param level must be >= 1
     * @return Level if it exists, otwerwise null.
     */
    @Override
    public Level retrieveLevel(int level, int nrOfQuestions) {
        Database database = DaggerLevelDatabaseFactory.create().database();
        Level tmp = database.getLevel(level);

        //reduce amount of questions in Level
        int notHigherThanThis = nrOfQuestions < tmp.getQuestions().length  ?
                nrOfQuestions : tmp.getQuestions().length;
        QuestionModel[] questions = Arrays.copyOfRange(tmp.getQuestions(), 0, notHigherThanThis);
        return new Level(questions, tmp.getTimeInSec());
    }
}
