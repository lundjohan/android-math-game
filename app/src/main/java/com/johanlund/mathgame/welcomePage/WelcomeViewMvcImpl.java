package com.johanlund.mathgame.welcomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.johanlund.mathgame.R;

public class WelcomeViewMvcImpl implements WelcomeViewMvc {
    View view;
    WelcomeViewMvc.Listener callback;
    private TextView levelNrView;
    private TextView difficultyView;
    private TextView descriptionView;
    private Button sendBtn;
    private SeekBar levelChooser;

    public WelcomeViewMvcImpl(WelcomeViewMvc.Listener listener, LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_welcome_fragment, container, false);
        callback = listener;
        levelNrView = view.findViewById(R.id.levelNr);
        difficultyView = view.findViewById(R.id.difficulty);
        descriptionView = view.findViewById(R.id.description);
        sendBtn = view.findViewById(R.id.sendBtn);
        levelChooser = view.findViewById(R.id.levelChooser);

        //Levels go from 1 to 15 for example. But Seekbar goes from 0 to 14, therefor +1.
        levelChooser.setMax(callback.getLastLevelNr()-1);
        levelChooser.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                levelNrView.setText(String.valueOf(progress + callback.getFirstLevelNr()));
                difficultyView.setText(callback.getInfoForLevelWithNr(progress+1).getDifficulty());
                descriptionView.setText(callback.getInfoForLevelWithNr(progress+1).getDescription());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sendBtn.setOnClickListener(v -> {
                    callback.startPressed();
                }
        );
    }

    @Override
    public View getRootView() {
        return view;
    }

    @Override
    public String retrieveLevelNrFromView() {
        return levelNrView.getText().toString();
    }
}
