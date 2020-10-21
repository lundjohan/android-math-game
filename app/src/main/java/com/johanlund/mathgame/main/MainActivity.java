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

import static com.johanlund.mathgame.common.Constants.LEVEL;
import static com.johanlund.mathgame.common.Constants.NR_OF_LEVEL;

public class MainActivity extends AppCompatActivity implements OneLevelFragmentListener {
    private int currentLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startLevel(true);

    }

    @Override
    public void changeLevelTo(int level) {
        currentLevel = level;
        startLevel(false);
    }

    private void startLevel(boolean beginningFragment) {
        int container = R.id.main_container;
        QuestionsProducer qp = new QuestionsProducerImpl();
        Level level = qp.retrieveLevel(currentLevel, 3);
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
}