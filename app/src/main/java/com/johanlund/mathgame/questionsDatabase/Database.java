package com.johanlund.mathgame.questionsDatabase;

import com.johanlund.mathgame.common.Level;
public interface Database {
    /**
     *
     * @param nr
     * @rule nr > 0
     * @return
     */
    Level getLevel(int nr);
}
