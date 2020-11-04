package com.johanlund.mathgame.welcomePage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.johanlund.mathgame.common.LevelInfo;

import static com.johanlund.mathgame.common.Constants.CHOOSEN_LEVEL;
import static com.johanlund.mathgame.common.Constants.INFO_ABOUT_LEVELS;

public class WelcomeFragment extends Fragment implements WelcomeViewMvc.Listener {
    private Listener callback;
    private LevelInfo[] infoAboutLevels;
    private WelcomeViewMvc viewMvc;
    final String TAG = getClass().getName();
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

        viewMvc = new WelcomeViewMvcImpl(this, inflater, container);

        // levels start at 1
        int minSeekBar = 1;
        //Levels go from 1 to n for example. But Seekbar goes from 0 to n-1.
        int maxSeekBar = infoAboutLevels.length - 1;
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

        if (savedInstanceState != null) {
            int chosenLevel = savedInstanceState.getInt(CHOOSEN_LEVEL);
            viewMvc.getLevelChooser().setProgress(chosenLevel);
        }
        else{
            // SeekBar::setProgress(0) seems to be disregarded (however setProgress(1) isn't),
            // I guess setOnSeekBarListener only operate on CHANGE.
            // we set views explicit instead
            if (infoAboutLevels.length>0){
                viewMvc.setLevelNrView(String.valueOf(1));
                viewMvc.setDifficultyView(infoAboutLevels[0].getDifficulty());
                viewMvc.setDescriptionView(infoAboutLevels[0].getDescription());
            }
        }
        return viewMvc.getRootView();
    }

    @Override
    public void startPressed() {
        callback.setCurrentLevel(Integer.valueOf(viewMvc.retrieveLevelNrFromView()));
        callback.startGame();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CHOOSEN_LEVEL,Integer.valueOf(viewMvc.retrieveLevelNrFromView()));
        super.onSaveInstanceState(outState);
    }

    public interface Listener {
        void setCurrentLevel(int level);
        void startGame();
    }
}
