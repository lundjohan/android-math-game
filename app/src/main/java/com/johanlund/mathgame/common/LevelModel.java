package com.johanlund.mathgame.common;

/**
 * uses:
 * works like a bridgde between json-file and Level.class
 */
public class LevelModel {


    private String[] questions;
    private int timeInSec;


    public LevelModel(String[] questions, int timeInSec) {
        this.questions = questions;
        this.timeInSec = timeInSec;
    }

    public String[] getQuestions() {
        return questions;
    }

    public int getTimeInSec() {
        return timeInSec;
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
            int indOfOperator = -1;

            //lookbehind etc to include delimeter in result:
            //  https://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
            //bit hacky, but it works...
            String[] threeParts = questions[i].split("((?<=[+*-/])|(?=[+*-/]))");
            int left = Integer.parseInt(threeParts[0]);
            int right = Integer.parseInt(threeParts[2]);
            char operator = threeParts[1].charAt(0);
            qs[i] = new QuestionModel(left, right, operator);
        }
        return new Level(qs,timeInSec);
    }
}


