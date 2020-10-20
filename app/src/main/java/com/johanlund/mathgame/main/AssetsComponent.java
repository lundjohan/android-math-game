package com.johanlund.mathgame.main;

import android.content.res.AssetManager;

import dagger.Component;

@Component(modules = AssetManagerModule.class)
public interface AssetsComponent {
    AssetManager assetManager();
}
