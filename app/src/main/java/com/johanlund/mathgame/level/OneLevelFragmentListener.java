package com.johanlund.mathgame.level;

public interface OneLevelFragmentListener {
    void levelCompleted();

    //The countdown time for level has reached zero
    void levelTimeIsUp();
}
