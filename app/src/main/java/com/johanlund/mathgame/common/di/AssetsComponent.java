package com.johanlund.mathgame.common.di;

import android.content.res.AssetManager;

import dagger.Component;

@Component(modules = AssetManagerModule.class)
public interface AssetsComponent {
    AssetManager assetManager();
}
