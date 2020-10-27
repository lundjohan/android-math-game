package com.johanlund.mathgame.welcomePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import com.johanlund.mathgame.common.LevelInfo;

import static com.johanlund.mathgame.common.Constants.INFO_ABOUT_LEVELS;

public class WelcomeFragment extends Fragment implements WelcomeViewMvc.Listener {
    private Listener callback;
    private LevelInfo[] infoAboutLevels;
    private WelcomeViewMvc viewMvc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = (Listener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        infoAboutLevels = (LevelInfo[]) args.getSerializable(INFO_ABOUT_LEVELS);

        // levels start at 1
        int minSeekBar = 1;

        //Levels go from 1 to n for example. But Seekbar goes from 0 to n-1, therefor +1.
        int maxSeekBar = infoAboutLevels.length - 1;
        viewMvc = new WelcomeViewMvcImpl(this, inflater, container);
        viewMvc.getLevelChooser().setMax(maxSeekBar);
        viewMvc.getLevelChooser().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewMvc.setLevelNrView(String.valueOf(progress + minSeekBar));
                viewMvc.setDifficultyView(infoAboutLevels[progress].getDifficulty());
                viewMvc.setDescriptionView(infoAboutLevels[progress].getDescription());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return viewMvc.getRootView();
    }

    @Override
    public void startPressed() {
        callback.setCurrentLevel(Integer.valueOf(viewMvc.retrieveLevelNrFromView()));
        callback.startGame();
    }

    public interface Listener {
        void setCurrentLevel(int level);

        void startGame();
    }
}
