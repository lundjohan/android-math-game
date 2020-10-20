package com.johanlund.mathgame.questionsDatabase;

import com.johanlund.mathgame.common.Level;

public interface Database {
    /**
     * @param nr
     * @return
     * @rule nr > 0
     */
    Level getLevel(int nr);
}
