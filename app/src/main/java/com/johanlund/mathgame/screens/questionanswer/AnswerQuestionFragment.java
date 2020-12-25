package com.johanlund.mathgame.screens.questionanswer;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.common.models.QuestionModel;
import com.johanlund.mathgame.databinding.FragmentQuestionAnswerBinding;

import static com.johanlund.mathgame.common.Constants.QUESTION_MODEL;

public class AnswerQuestionFragment extends Fragment {
    private AnswerQuestionViewModel viewModel;
    private View view;
    private TextView questionView;
    private EditText answerBox;
    private AnswerQuestionFragment.Listener callback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = (AnswerQuestionFragment.Listener) getParentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //initiate binding
        FragmentQuestionAnswerBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_question_answer, container, false);

        Bundle args = getArguments();
        QuestionModel qm = (QuestionModel) args.getParcelable(QUESTION_MODEL);


        // Get the viewmodel (NB, that this doesnt necessarily mean creating new ViewModel)
        AnswerQuestionViewModelFactory viewModelfactory = new AnswerQuestionViewModelFactory(qm);
        viewModel = new ViewModelProvider(this, viewModelfactory).get(AnswerQuestionViewModel.class);

        //two lines necessary for binding to work
        binding.setAnswerQuestionViewModel(viewModel);
        binding.setLifecycleOwner(this);

        view = binding.getRoot();

        questionView = binding.mathQuestion;
        questionView.setText(qm.getLeft() + " " + qm.getOperator() + " " + qm.getRight() + " =");

        answerBox = binding.userAnswerInput;
        answerBox.setRawInputType(Configuration.KEYBOARD_12KEY);

        Button sendBtn = binding.sendBtn;
        sendBtn.setOnClickListener(v -> {
            String str = answerBox.getText().toString();
            if (str.isEmpty()) {
                answerBox.setError("Cannot be empty!");
            } else {
                viewModel.checkAnswer(Integer.valueOf(str));
            }
        });

        //answer is correct
        viewModel.isAnswerCorrect().observe(getViewLifecycleOwner(), isCorrect -> {
            if (isCorrect) {
                callback.answerIsCorrect(viewModel.getQuestion().toString());
            }
        });

        //answer is incorrect
        viewModel.isAnswerIncorrect().observe(getViewLifecycleOwner(), isIncorrect -> {
            if (isIncorrect) {
                doIncorrectGraphics();
            }
        });
        return binding.getRoot();
    }

    private void doIncorrectGraphics() {
        view.setBackgroundColor(Color.RED);
    }

    private QuestionModel retrieveQuestionFromView() {
        String[] parts = questionView.getText().toString().split(" ");
        if (parts.length == 0) {
            return null;
        }
        QuestionModel qm = new QuestionModel(Integer.valueOf(parts[0]), Integer.valueOf(parts[2]), parts[1].charAt(0));
        return qm;
    }

    public interface Listener {
        void answerIsCorrect(String id);
    }
}

