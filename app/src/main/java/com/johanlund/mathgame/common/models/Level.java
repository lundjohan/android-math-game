package com.johanlund.mathgame.common.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Level implements Parcelable {
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

    protected Level(Parcel in) {
        questions = in.createTypedArray(QuestionModel.CREATOR);
        timeInSecPerQuestion = in.readInt();
    }

    public static final Creator<Level> CREATOR = new Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel in) {
            return new Level(in);
        }

        @Override
        public Level[] newArray(int size) {
            return new Level[size];
        }
    };

    public QuestionModel[] getQuestions() {
        return questions;
    }

    public int getTimeInSecPerQuestion() {
        return timeInSecPerQuestion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(questions, flags);
        dest.writeInt(timeInSecPerQuestion);
    }
}
