package com.johanlund.mathgame.main;

import android.content.res.AssetManager;

import dagger.Module;
import dagger.Provides;

@Module
public class AssetManagerModule {
    private static AssetManager assets;

    public AssetManagerModule() {
    }

    public AssetManagerModule(AssetManager assets) {
        this.assets = assets;
    }

    @Provides
    public AssetManager provideAssets() {
        return assets;
    }
}
