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

    @Override
    public SeekBar getLevelChooser(){
        return levelChooser;
    }

    @Override
    public void setDescriptionView(String description) {
        descriptionView.setText(description);
    }

    @Override
    public void setDifficultyView(String difficulty) {
        difficultyView.setText(difficulty);
    }

    @Override
    public void setLevelNrView(String valueOf) {
        levelNrView.setText(valueOf);
    }
}
