package com.johanlund.mathgame.questionanswer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.QuestionModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.johanlund.mathgame.common.Constants.LEVEL;
import static com.johanlund.mathgame.common.Constants.QUESTION_MODEL;

public class OneLevelFragment extends Fragment {
    private OneLevelFragmentListener callback;
    QuestionAdapter questionAdapter;
    ViewPager2 viewPager;
    List<QuestionModel> qs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            Level level = (Level) args.getSerializable(LEVEL);
            qs = Arrays.asList(level.getQuestions());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callback = (OneLevelFragmentListener) getActivity();


        return inflater.inflate(R.layout.fragment_one_level, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        questionAdapter = new QuestionAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(questionAdapter);
    }

    /**
     * INNER CLASS ADAPTER
     */

    public class QuestionAdapter extends FragmentStateAdapter {
        public QuestionAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return a NEW fragment instance in createFragment(int)
            Fragment fragment = new OneQuestionFragment();
            Bundle args = new Bundle();
            args.putSerializable(QUESTION_MODEL, qs.get(position));
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return qs.size();
        }
    }
}

