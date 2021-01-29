package com.johanlund.mathgame.screens.level;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.johanlund.mathgame.App;
import com.johanlund.mathgame.R;
import com.johanlund.mathgame.databinding.FragmentLevelBinding;
import com.johanlund.mathgame.debug.BackStackLogger;
import com.johanlund.mathgame.screens.answerquestion.AnswerQuestionFragment;

public class LevelFragment extends Fragment implements AnswerQuestionFragment.Listener {
    private LevelViewModel viewModel;
    private QuestionAdapter questionsAdapter;
    private ViewPager2 viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //initiate binding
        FragmentLevelBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_level, container, false);

        int chosenLevel = LevelFragmentArgs.fromBundle(getArguments()).getLevelNr();

        // Get the viewmodel (NB, that this doesnt necessarily mean creating new ViewModel)
        LevelViewModelFactory viewModelfactory = new LevelViewModelFactory(((App)getActivity().getApplication()).getQuestionProducer(), chosenLevel);
        viewModel = new ViewModelProvider(this, viewModelfactory).get(LevelViewModel.class);

        //two lines necessary for binding to work
        binding.setLevelViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewPager = binding.pager;
        initateViewPager();

        //score
        viewModel.getCurrentScore().observe(getViewLifecycleOwner(), scoreStr -> {
            binding.score.setText(scoreStr);
        });

        //levelnr
        viewModel.getCurrentLevel().observe(getViewLifecycleOwner(), levelNr -> {
            binding.levelTitle.setText("Level " + levelNr);
        });

        //time
        viewModel.getSecRemaining().observe(getViewLifecycleOwner(), secs -> {
            binding.timeLeft.setText("" + secs);
        });

        //time is up for level
        viewModel.isTimeFinished().observe(getViewLifecycleOwner(), isFinished -> {
            if (isFinished) {
                new OkDialog().startDialog(this, R.string.time_is_up, R.string.moving_down);
                viewModel.onTimeIsFinishedComplete();

            }
        });

        //level is completed, but not last last level
        viewModel.isLevelCompleted().observe(getViewLifecycleOwner(), isFinished -> {
            if (isFinished) {
                new OkDialog().startDialog(this, R.string.well_done, R.string.moving_up);
                viewModel.onFinishedLevelComplete();

            }
        });

        //whole game (last level) is finished
        viewModel.isGameFinished().observe(getViewLifecycleOwner(), isFinished -> {
            if (isFinished) {
                int action = LevelFragmentDirections.actionLevelToWin().getActionId();
                NavHostFragment.findNavController(this).navigate(action);
                viewModel.onFinishedGameComplete();
            }
        });


        //set up logger for backstack
        new BackStackLogger(this.getClass().getName(), getParentFragmentManager()).log();
        return binding.getRoot();
    }

    @Override
    public void answerIsCorrect(String questionModel) {
        questionsAdapter.popQuestion(questionModel);
        viewModel.increaseScoreByOne();
    }

    void initateViewPager() {
        questionsAdapter = new QuestionAdapter(this, viewModel.getQuestionsList());
        //is this one needed?
        viewPager.requestTransform();

        viewPager.setAdapter(questionsAdapter);
    }

    //INNER CLASS handling Dialogs that will change level up or down
    private class OkDialog {
        private void startDialog(Fragment fragment, int title, int msg) {
            AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getContext());
            builder.setPositiveButton(R.string.ok, (dialog, id) -> {
                        viewModel.initiateNewLevel();
                        initateViewPager();
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
