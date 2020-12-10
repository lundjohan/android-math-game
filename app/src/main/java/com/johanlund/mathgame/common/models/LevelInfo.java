package com.johanlund.mathgame.common.models;

import java.io.Serializable;

public class LevelInfo implements Serializable {
    private String difficulty;
    private String description;

    public LevelInfo(String difficulty, String description) {
        this.difficulty = difficulty;
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }
}
