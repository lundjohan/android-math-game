package com.johanlund.mathgame.questionsDatabase;

import dagger.Component;

@Component
public interface LevelDatabaseFactory {
    Database database();
}
