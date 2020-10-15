package com.johanlund.mathgame;

import android.app.Application;
import com.johanlund.mathgame.main.AssetManagerModule;
import com.johanlund.mathgame.main.DaggerAssetsComponent;

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAssetsComponent.builder().
                assetManagerModule(new AssetManagerModule(getApplicationContext().
                        getAssets())).build();
    }
}
