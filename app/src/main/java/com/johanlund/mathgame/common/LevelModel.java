package com.johanlund.mathgame.common;

import com.johanlund.mathgame.util.Util;

/**
 * uses:
 * works like a bridge between json-file and Level.class
 */
public class LevelModel {


    private String[] questions;
    private int timeInSecPerQuestion;


    public LevelModel(String[] questions, int timeInSec) {
        this.questions = questions;
        this.timeInSecPerQuestion = timeInSec;
    }

    /**
     * json input: ...
     * "questions": [
     * "2+2","2+3",  ...
     *
     * @return
     */
    public Level toLevel() {
        QuestionModel qs[] = new QuestionModel[questions.length];
        for (int i = 0; i < qs.length; i++) {
            qs[i] = Util.strToQuestionModel(questions[i]);
        }
        return new Level(qs, timeInSecPerQuestion);
    }
}


