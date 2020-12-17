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
import com.johanlund.mathgame.debug.BackStackLogger;

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

                //If it is set to ...navigate(actionid) => levelNr will not be conserved to next destination.
                NavHostFragment.findNavController(this).navigate(action);

                /*This is needed in case you move back to this fragment, otherwise you will immidiately go to next fragment again*/
                viewModel.onStartLevelComplete();
            }
        });
        new BackStackLogger(this.getClass().getName(),getParentFragmentManager()).log();
        return binding.getRoot();
    }
}
