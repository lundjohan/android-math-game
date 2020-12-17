package com.johanlund.mathgame.screens.win;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.databinding.FragmentWinBinding;
import com.johanlund.mathgame.debug.BackStackLogger;

public class WinFragment extends Fragment {
    private WinViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWinBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_win, container, false);

        // Get the viewmodel
        viewModel = new ViewModelProvider(this).get(WinViewModel.class);

        //two lines necessary for binding to work
        binding.setWinViewModel(viewModel);
        binding.setLifecycleOwner(this);


        viewModel.isGameFinished().observe(getViewLifecycleOwner(), isFinished -> {
            if (isFinished) {
                int action = WinFragmentDirections.actionWinToWelcome().getActionId();
                NavHostFragment.findNavController(this).navigate(action);
            }
        });
        new BackStackLogger(this.getClass().getName(),getParentFragmentManager()).log();
        return binding.getRoot();
    }
}
