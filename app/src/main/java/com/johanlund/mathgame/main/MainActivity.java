package com.johanlund.mathgame.main;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.LevelInfo;
import com.johanlund.mathgame.level.OneLevelFragment;
import com.johanlund.mathgame.questionsProducer.QuestionsProducer;
import com.johanlund.mathgame.questionsProducer.QuestionsProducerImpl;
import com.johanlund.mathgame.welcomePage.WelcomeFragment;
import com.johanlund.mathgame.win.WinFragment;

import static com.johanlund.mathgame.common.Constants.INFO_ABOUT_LEVELS;
import static com.johanlund.mathgame.common.Constants.LEVEL;
import static com.johanlund.mathgame.common.Constants.NR_OF_LEVEL;
import static com.johanlund.mathgame.common.Constants.TOT_NR_OF_LEVELS;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.Listener,OneLevelFragment.Listener {
    private final int QUESTIONS_PER_LEVEL = 5;
    private int totNrOfLevels;
    private LevelInfo [] infoAboutLevels;
    private int currentLevel = 1;
    int container;
    ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = R.id.main_container;
        toolbar = getSupportActionBar();

        QuestionsProducer qp = new QuestionsProducerImpl();
        totNrOfLevels = qp.getTotalNrOfLevels();
        infoAboutLevels = qp.getLevelInfos();
        startWelcomePage();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //android.R.id.home is the top back button
            case android.R.id.home:
                startWelcomePage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startWelcomePage(){
        Bundle args = new Bundle();
        args.putSerializable(INFO_ABOUT_LEVELS, infoAboutLevels);

        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();

        //This IS the start page and home arrow should not be displayed
        toolbar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void setCurrentLevel(int level){
        currentLevel = level;
    }

    @Override
    public void startGame() {
        startLevel();
    }

    @Override
    public void levelCompleted() {
        if (currentLevel == totNrOfLevels) {
            showWin();
            return;
        }
        ++currentLevel;
        new OkDialog().startDialog(this, R.string.well_done, R.string.moving_up);

    }


    @Override
    public void levelTimeIsUp() {
        //If possible: move down one level
        currentLevel = currentLevel > 1 ? currentLevel - 1 : 1;
        new OkDialog().startDialog(this, R.string.time_is_up, R.string.moving_down);
    }

    private void startLevel() {
        QuestionsProducer qp = new QuestionsProducerImpl();
        Level level = qp.getLevel(currentLevel, QUESTIONS_PER_LEVEL);

        Bundle args = new Bundle();
        args.putSerializable(LEVEL, level);
        args.putInt(NR_OF_LEVEL, currentLevel);

        OneLevelFragment fragment = new OneLevelFragment();
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();

        //User should be able to go to start page from this screen
        toolbar.setDisplayHomeAsUpEnabled(true);
    }

    private void showWin() {
        WinFragment fragment = new WinFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();

        //User should be able to go to start page from this screen
        toolbar.setDisplayHomeAsUpEnabled(true);
    }

    //INNER CLASS handling Dialogs
    private class OkDialog {
        private void startDialog(Context c, int title, int msg) {
            AlertDialog.Builder builder = new AlertDialog.Builder(c);
            builder.setPositiveButton(R.string.ok, (dialog, id) -> {
                        startLevel();
                    }
            );
            final AlertDialog dialog = builder.setMessage(msg)
                    .setTitle(title)
                    .create();
            dialog.show();

            //Put OK btn in the centre
            final Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            LinearLayout.LayoutParams positiveButtonLL = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
            positiveButtonLL.width = ViewGroup.LayoutParams.MATCH_PARENT;
            positiveButton.setLayoutParams(positiveButtonLL);
        }
    }
}