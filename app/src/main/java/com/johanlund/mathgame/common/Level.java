package com.johanlund.mathgame.common;

import java.io.Serializable;

public class Level implements Serializable {
    /**
     * questions should be unique inside one level!
     */
    private QuestionModel[] questions;
    private int timeInSecPerQuestion;


    public Level(QuestionModel[] questions, int timeInSecPerQuestion) {
        this.questions = questions;
        this.timeInSecPerQuestion = timeInSecPerQuestion;
    }

    public Level(String[] questions, int timeInSec) {

    }

    public QuestionModel[] getQuestions() {
        return questions;
    }

    public int getTimeInSecPerQuestion() {
        return timeInSecPerQuestion;
    }
}
