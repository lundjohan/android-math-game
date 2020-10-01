package com.johanlund.mathgame.common;

//immutable class
public class QuestionModel {
    private final int left;
    private final int right;
    private final char operator;
    public QuestionModel(int aLeft, int aRight, char aOperator){
        left = aLeft;
        right = aRight;
        operator = aOperator;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public char getOperator() {
        return operator;
    }
}
