package com.johanlund.mathgame.screens.win;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.screens.level.OneLevelFragmentDirections;
import com.johanlund.mathgame.screens.welcomePage.WelcomeFragmentDirections;

public class WinFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_win, container, false);

        //Click on New Game Btn takes us back to Welcome Page
        Button newGameBtn = v.findViewById(R.id.newGameBtn);
        newGameBtn.setOnClickListener(c -> {
            int action = WinFragmentDirections.actionWinToWelcome().getActionId();
            NavHostFragment.findNavController(this).navigate(action);
        });
        return v;
    }
}
