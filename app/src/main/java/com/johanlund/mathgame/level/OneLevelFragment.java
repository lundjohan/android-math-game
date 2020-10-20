package com.johanlund.mathgame.level;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.johanlund.mathgame.common.Level;
import com.johanlund.mathgame.common.QuestionModel;

import java.util.Arrays;
import java.util.List;

import static com.johanlund.mathgame.common.Constants.LEVEL;

public class OneLevelFragment extends Fragment {
    private OneLevelViewMvc viewMvc;
    private OneLevelFragmentListener callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        callback = (OneLevelFragmentListener) getActivity();
        viewMvc = new OneLevelViewMvcImpl(inflater, container);
        return viewMvc.getRootView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //is it really correct to retrieve  level here? Check lifecycles!
        Bundle args = getArguments();
        if (args != null) {
            Level level = (Level) args.getSerializable(LEVEL);
            List<QuestionModel> qms = Arrays.asList(level.getQuestions());
            QuestionAdapter adapter = new QuestionAdapter(this, qms);
            viewMvc.bindPagerToView(adapter);
        }
    }
}

