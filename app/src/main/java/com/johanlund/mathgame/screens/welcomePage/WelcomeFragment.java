package com.johanlund.mathgame.screens.welcomePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.databinding.FragmentWelcomeBinding;

public class WelcomeFragment extends Fragment {
    private WelcomeViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentWelcomeBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_welcome, container, false);

        // Get the viewmodel
        viewModel = new ViewModelProvider(this).get(WelcomeViewModel.class);

        //two lines necessary for binding to work
        binding.setWelcomeViewModel(viewModel);
        binding.setLifecycleOwner(this);

        //binding.levelChooser.setMin(1);
        binding.levelChooser.setMax(Integer.valueOf(viewModel.getNrOfLastLevel()));
        binding.levelChooser.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                viewModel.onSeekBarChange(progress);

            }
        });

        //Seekbar only operates on change, and no event is fired if it hasn't change, which we need at start
        if (viewModel.getChosenLevel().getValue() == "1") {
            viewModel.onSeekBarChange(0);
        }

        viewModel.isStartGamePressed().observe(getViewLifecycleOwner(), isPressed -> {
            if (isPressed) {
                String chosenLevel = viewModel.getChosenLevel().getValue();
                WelcomeFragmentDirections.ActionWelcomeToLevel action = WelcomeFragmentDirections.actionWelcomeToLevel().setLevelNr(Integer.valueOf(chosenLevel));
                //N.B. If code below is replaced with navigate(action) => orientation change => press start btn => crasch.
                NavHostFragment.findNavController(this).navigate(action.getActionId());
            }
        });
        return binding.getRoot();
    }
}

/*
        if (savedInstanceState != null) {
            int chosenLevel = savedInstanceState.getInt(CHOOSEN_LEVEL);
            viewMvc.getLevelChooser().setProgress(chosenLevel);
        } else {
            // SeekBar::setProgress(0) seems to be disregarded (however setProgress(1) isn't),
            // I guess setOnSeekBarListener only operate on CHANGE.
            // we set views explicit instead
            if (infoAboutLevels.length > 0) {
                viewMvc.setLevelNrView(String.valueOf(1));
                viewMvc.setDifficultyView(infoAboutLevels[0].getDifficulty());
                viewMvc.setDescriptionView(infoAboutLevels[0].getDescription());
            }
        }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CHOOSEN_LEVEL, Integer.valueOf(viewMvc.retrieveLevelNrFromView()));
        super.onSaveInstanceState(outState);
    }
}*/
