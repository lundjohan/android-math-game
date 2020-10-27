package com.johanlund.mathgame.questionsDatabase;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.LevelInfo;
import com.johanlund.mathgame.common.LevelModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.inject.Inject;

public class JSONDatabase implements Database {
    private final String FILENAME = "levels.json";
    private AssetManager assetManager;
    private final String TAG = "JSONDatabase";

    @Inject
    public JSONDatabase(AssetManager assets) {
        assetManager = assets;
    }

    @Override
    public Level getLevel(int nr) throws IllegalArgumentException {
        LevelModel[] levelModels;
        Level toReturn = null;

        if (nr < 1) {
            throw new IllegalArgumentException("Levels should start at 1!");
        }

        levelModels = getLevelModels();
        try {
            //Json level starting at 0, but this API counts on levels starting at 1.
            toReturn = levelModels[nr - 1].toLevel();
        } catch (IndexOutOfBoundsException e) {
            Log.e(TAG, "Inside getLevel. " +
                    "Index is higher than the arrays elements, null will be returned");

        }
        return toReturn;
    }

    @Override
    public int getNrOfLevels() {
        return getLevelModels().length;
    }

    @Override
    public LevelInfo[] getLevelInfos() {
        LevelModel [] levelModels = getLevelModels();
        LevelInfo [] levelInfos = new LevelInfo[levelModels.length];
        for (int i = 0;i<levelModels.length;i++){
            levelInfos[i] = levelModels[i].toLevelInfo();
        }
        return levelInfos;
    }

    private LevelModel[] getLevelModels() {
        LevelModel[] levelModels = null;
        try {
            Gson gson = new Gson();
            InputStream stream = assetManager.open(FILENAME);
            Reader reader = new InputStreamReader(stream, "UTF-8");
            levelModels = gson.fromJson(reader, LevelModel[].class);
        } catch (IOException e) {
            Log.e(TAG, e.toString() +
                    "Something is wrong with reading from JSON file. Null will be returned.");
        }
        return levelModels;
    }
}
