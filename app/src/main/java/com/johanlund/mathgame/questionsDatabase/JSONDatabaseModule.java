package com.johanlund.mathgame.questionsDatabase;

import dagger.Module;
import dagger.Binds;

@Module
public abstract class JSONDatabaseModule {
    @Binds
    public abstract Database jSONDatabase(JSONDatabase jsonDatabase);
}
