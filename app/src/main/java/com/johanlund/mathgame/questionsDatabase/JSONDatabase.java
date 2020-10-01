package com.johanlund.mathgame.questionsDatabase;

import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.QuestionModel;

import javax.inject.Inject;

public class JSONDatabase implements Database{

    @Inject
    public JSONDatabase(){

    }
    @Override
    public Level getLevel(int nr) {
        QuestionModel qm1 = new QuestionModel(2,4,'*');
        QuestionModel[] questions = {qm1};
        return new Level(questions, 90);
    }
}
