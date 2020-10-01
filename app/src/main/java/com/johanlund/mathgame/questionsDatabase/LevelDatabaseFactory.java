package com.johanlund.mathgame.questionsDatabase;

import dagger.Component;

@Component(modules = JSONDatabaseModule.class)
public interface LevelDatabaseFactory {
    Database database();
}
