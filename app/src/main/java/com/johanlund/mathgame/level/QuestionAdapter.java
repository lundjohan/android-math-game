package com.johanlund.mathgame.level;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.johanlund.mathgame.common.QuestionModel;
import com.johanlund.mathgame.questionanswer.AnswerQuestionFragment;

import java.util.List;

import static com.johanlund.mathgame.common.Constants.QUESTION_MODEL;

public class QuestionAdapter extends FragmentStateAdapter {
    List<QuestionModel> qms;
    public QuestionAdapter(@NonNull Fragment fragment, List<QuestionModel> qms) {
        super(fragment);
        this.qms = qms;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new AnswerQuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUESTION_MODEL, qms.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return qms.size();
    }
}
