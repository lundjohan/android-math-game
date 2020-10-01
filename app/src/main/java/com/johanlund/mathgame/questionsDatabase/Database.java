package com.johanlund.mathgame.questionsDatabase;

import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.QuestionModel;

import javax.inject.Inject;


final public class Database {
    @Inject
    public Database(){

    }
    //temporary stub
    public Level getLevel(int nr){
        QuestionModel qm1 = new QuestionModel(5,4,'*');
        QuestionModel qm2 = new QuestionModel(6,3,'-');
        QuestionModel [] questions = {qm1, qm2};
        return new Level(questions, 90);
    }
}
