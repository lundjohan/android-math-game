package com.johanlund.mathgame.questionsDatabase;

import com.johanlund.mathgame.common.di.AssetManagerModule;

import dagger.Component;

@Component(modules = {AssetManagerModule.class, JSONDatabaseModule.class})
public interface LevelDatabaseFactory {
    JSONDatabase database();
}
