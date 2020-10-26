package com.johanlund.mathgame.welcomePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.common.LevelInfo;

import static com.johanlund.mathgame.common.Constants.INFO_ABOUT_LEVELS;
import static com.johanlund.mathgame.common.Constants.TOT_NR_OF_LEVELS;

public class WelcomeFragment extends Fragment {
    private Listener callback;
    private LevelInfo[] infoAboutLevels;
    private TextView levelNrView;
    private TextView difficultyView;
    private TextView descriptionView;
    private Button sendBtn;
    private SeekBar levelChooser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = (Listener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome_fragment, container, false);
        Bundle args = getArguments();
        infoAboutLevels = (LevelInfo[]) args.getSerializable(INFO_ABOUT_LEVELS);

        levelNrView = view.findViewById(R.id.levelNr);
        difficultyView = view.findViewById(R.id.difficulty);
        descriptionView = view.findViewById(R.id.description);
        sendBtn = view.findViewById(R.id.sendBtn);
        levelChooser = view.findViewById(R.id.levelChooser);

        // levels start at 1
        int minSeekBar = 1;
        int maxSeekBar = args.getInt(TOT_NR_OF_LEVELS);

        levelChooser.setMax(maxSeekBar - 1);
        levelChooser.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                levelNrView.setText("Level "+ String.valueOf(progress + minSeekBar));
                difficultyView.setText(infoAboutLevels[progress].getDifficulty());
                descriptionView.setText(infoAboutLevels[progress].getDescription());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sendBtn.setOnClickListener(v -> {
                    callback.setCurrentLevel(Integer.valueOf(levelNrView.getText().toString()));
                    callback.startGame();
                }
        );
        return view;
    }

    public interface Listener {
        void setCurrentLevel(int level);

        void startGame();
    }
}
