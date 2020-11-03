package com.johanlund.mathgame.common;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

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


}
