package com.johanlund.mathgame.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.level.OneLevelFragment;
import com.johanlund.mathgame.level.OneLevelFragmentListener;
import com.johanlund.mathgame.questionsProducer.QuestionsProducer;
import com.johanlund.mathgame.questionsProducer.QuestionsProducerImpl;
import com.johanlund.mathgame.win.WinFragment;

import static com.johanlund.mathgame.common.Constants.LEVEL;
import static com.johanlund.mathgame.common.Constants.NR_OF_LEVEL;

public class MainActivity extends AppCompatActivity implements OneLevelFragmentListener {
    private int currentLevel = 4;
    int container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = R.id.main_container;
        startLevel(true);

    }

    @Override
    public void changeLevelTo(int level) {
        currentLevel = level;
        startLevel(false);
    }

    private void startLevel(boolean beginningFragment) {
        QuestionsProducer qp = new QuestionsProducerImpl();
        Level level = qp.retrieveLevel(currentLevel, 5);

        //null means that there are no more levels to get.
        if (level == null){
            showWin();
            return;
        }

        Bundle args = new Bundle();
        args.putSerializable(LEVEL, level);
        args.putInt(NR_OF_LEVEL, currentLevel);

        OneLevelFragment fragment = new OneLevelFragment();
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (beginningFragment) {
            transaction.add(container, fragment);
        } else {
            transaction.replace(container, fragment);
        }
        transaction.commit();
    }

    private void showWin() {
        WinFragment fragment = new WinFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();
    }
}