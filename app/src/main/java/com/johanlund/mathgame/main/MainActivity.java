package com.johanlund.mathgame.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
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
    private final int QUESTIONS_PER_LEVEL = 2;
    private int totNrOfLevels;
    private int currentLevel = 3;
    int container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = R.id.main_container;

        QuestionsProducer qp = new QuestionsProducerImpl();
        totNrOfLevels = qp.getTotalNrOfLevels();
        startLevel(true);

    }

    @Override
    public void levelCompleted() {
        if (currentLevel == totNrOfLevels) {
            showWin();
            return;
        }
        ++currentLevel;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startLevel(false);
            }
        });
        final AlertDialog dialog = builder.setMessage(R.string.moving_up)
                .setTitle(R.string.well_done)
                .create();
                dialog.show();

        //Put OK btn in the centre
        final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
        positiveButton.setLayoutParams(positiveButtonLL);
    }

    @Override
    public void timeIsUp() {
        //Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //If possible: move down one level
                currentLevel = currentLevel > 1 ? currentLevel - 1 : 1;

                startLevel(false);
            }
        });
        final AlertDialog dialog = builder.setMessage(R.string.moving_down)
                .setTitle(R.string.time_is_up)
                .create();
        dialog.show();

        //Put OK btn in the centre
        final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
        positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
        positiveButton.setLayoutParams(positiveButtonLL);
    }

    private void startLevel(boolean beginningFragment) {
        QuestionsProducer qp = new QuestionsProducerImpl();
        Level level = qp.retrieveLevel(currentLevel, QUESTIONS_PER_LEVEL);

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

    private void createDialogWithOkBtn() {

    }
}