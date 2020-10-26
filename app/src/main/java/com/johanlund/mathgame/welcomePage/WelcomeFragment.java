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

import static com.johanlund.mathgame.common.Constants.TOT_NR_OF_LEVELS;

public class WelcomeFragment extends Fragment {
    Listener callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = (Listener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome_fragment, container, false);
        TextView levelNrView = view.findViewById(R.id.levelNr);

        Button sendBtn = view.findViewById(R.id.sendBtn);

        Bundle args = getArguments();

        SeekBar levelChooser = view.findViewById(R.id.levelChooser);

        // levels start at 1
        int minSeekBar = 1;
        int maxSeekBar = args.getInt(TOT_NR_OF_LEVELS);

        levelChooser.setMax(maxSeekBar - 1);
        levelChooser.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                levelNrView.setText(String.valueOf(progress + minSeekBar));
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
