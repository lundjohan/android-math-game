package com.johanlund.mathgame.common;

import java.io.Serializable;

public class Level implements Serializable {
    /**
     * questions should be unique inside one level!
     */
    private QuestionModel[] questions;
    private int timeInSec;


    public Level(QuestionModel[] questions, int timeInSec) {
        this.questions = questions;
        this.timeInSec = timeInSec;
    }

    public Level(String[] questions, int timeInSec) {

    }

    public QuestionModel[] getQuestions() {
        return questions;
    }

    public int getTimeInSec() {
        return timeInSec;
    }
}
