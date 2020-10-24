package com.johanlund.mathgame.common;

import java.io.Serializable;

//immutable class
public class QuestionModel implements Serializable {
    private final int left;
    private final int right;
    private final char operator;

    public QuestionModel(int aLeft, int aRight, char aOperator) {
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

    @Override
    public String toString() {
        return "" + left + " " + operator + " " + right;
    }
}
