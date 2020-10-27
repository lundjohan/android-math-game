package com.johanlund.mathgame.welcomePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        viewMvc = new WelcomeViewMvcImpl(this, inflater, container);
        return viewMvc.getRootView();
    }

    @Override
    public void startPressed() {
        callback.setCurrentLevel(Integer.valueOf(viewMvc.retrieveLevelNrFromView()));
        callback.startGame();
    }

    @Override
    public int getFirstLevelNr() {
        return 1;
    }

    @Override
    public int getLastLevelNr() {
        return infoAboutLevels.length;
    }

    @Override
    public LevelInfo getInfoForLevelWithNr(int levelNr) {
        return infoAboutLevels[levelNr-1];
    }

    public interface Listener {
        void setCurrentLevel(int level);

        void startGame();
    }
}
