package com.johanlund.mathgame.common;

public class Level {
    private QuestionModel [] questions;
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
