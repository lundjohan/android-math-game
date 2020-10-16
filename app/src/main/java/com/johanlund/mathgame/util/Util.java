package com.johanlund.mathgame.util;

import com.johanlund.mathgame.common.QuestionModel;

public class Util {

    /**
     *
     * @param s"222+3" or "99/33"
     * @return QuestionModel(left = 222, right = 3, operator = +)
     */
    public static QuestionModel strToQuestionModel(String s){
        //lookbehind etc to include delimeter in result:
        //  https://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters
        //bit hacky, but it works...
        String[] threeParts = s.split("((?<=[+*-/])|(?=[+*-/]))");
        int left = Integer.parseInt(threeParts[0]);
        int right = Integer.parseInt(threeParts[2]);
        char operator = threeParts[1].charAt(0);
        return new QuestionModel(left, right, operator);
    }
}
