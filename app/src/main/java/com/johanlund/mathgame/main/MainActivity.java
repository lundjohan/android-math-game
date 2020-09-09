package com.johanlund.mathgame.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.questionanswer.AnswerQuestionFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new AnswerQuestionFragment();
        FragmentManager transaction = getSupportFragmentManager();
        transaction.beginTransaction()
                .add(R.id.main_container, fragment, "QuestionAnswerFragment")
                .commit();
    }
}