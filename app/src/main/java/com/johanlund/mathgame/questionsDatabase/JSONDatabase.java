package com.johanlund.mathgame.questionsDatabase;

import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.LevelModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class JSONDatabase implements Database {
    private final String TAG = "JSONDatabase";
    AssetManager assetManager;

    @Inject
    public JSONDatabase(AssetManager assets) {

        assetManager = assets;
    }

    @Override
    public Level getLevel(int nr) throws IllegalArgumentException {
        LevelModel[] levelModels;
        Level toReturn = null;

        if (nr < 1){
            throw new IllegalArgumentException("Levels should start at 1!");
        }

        try {
            Gson gson = new Gson();
            InputStream stream = assetManager.open("levels.json");
            Reader reader = new InputStreamReader(stream, "UTF-8");
            levelModels = gson.fromJson(reader, LevelModel[].class);

            //Json level starting at 0, but this API counts on levels starting at 1.
            toReturn = levelModels[nr-1].toLevel();
        }
        catch (IOException e) {
                Log.e(TAG, e.toString());
        }
        catch (IndexOutOfBoundsException e) {
            Log.e(TAG,"Inside getLevel. " +
                    "Index is higher than the arrays elements, null will be returned");

        }
        return toReturn;
    }
}
