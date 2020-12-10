package com.johanlund.mathgame.common.models;

import android.os.Parcel;
import android.os.Parcelable;

//immutable class
public class QuestionModel implements Parcelable {
    private final int left;
    private final int right;
    private final char operator;

    public QuestionModel(int aLeft, int aRight, char aOperator) {
        left = aLeft;
        right = aRight;
        operator = aOperator;
    }

    protected QuestionModel(Parcel in) {
        left = in.readInt();
        right = in.readInt();
        operator = (char) in.readInt();
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(left);
        dest.writeInt(right);
        dest.writeInt((int) operator);
    }

    @Override
    public String toString() {
        return "" + left + " " + operator + " " + right;
    }

    /**
     * Util method
     * @param s"222+3" or "99/33"
     * @return QuestionModel(left = 222, right = 3, operator = +)
     */
    public static QuestionModel strToQuestionModel(String s) {
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
