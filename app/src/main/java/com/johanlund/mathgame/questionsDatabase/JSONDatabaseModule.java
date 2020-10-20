package com.johanlund.mathgame.questionsDatabase;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class JSONDatabaseModule {
    @Binds
    public abstract Database jSONDatabase(JSONDatabase jsonDatabase);
}
