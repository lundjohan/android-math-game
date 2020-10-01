package com.johanlund.mathgame.questionanswer;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.johanlund.mathgame.R;
import com.johanlund.mathgame.common.QuestionModel;

public class AnswerQuestionViewMvcImpl implements AnswerQuestionViewMvc{
    private View view;
    private TextView questionView;
    private EditText answerBox;
    private AnswerQuestionViewMvc.Listener listener;

    public AnswerQuestionViewMvcImpl(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_question_answer, container, false);
        questionView = view.findViewById(R.id.mathQuestion);
        Button sendBtn = view.findViewById(R.id.sendBtn);
        answerBox = view.findViewById(R.id.userAnswerInput);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listener.checkAnswer();
            }
        });
    }

    //must control for correct input
    public Integer retrieveAnswer(){
        String str = answerBox.getText().toString();
        if (str.isEmpty()){
            answerBox.setError("Cannot be empty!");
            return null;
        }
        return Integer.valueOf(str);
    }

    @Override
    public void setListener(Listener l) {
        listener = l;
    }

    @Override
    public void unregisterListener() {
        listener = null;
    }

    @Override
    public void bindQuestionToView(QuestionModel qm) {
        questionView.setText(qm.getLeft() + " " + qm.getOperator() + " " + qm.getRight() + " =");
    }

    @Override
    public QuestionModel retrieveQuestionFromView() {
        String [] parts = questionView.getText().toString().split(" ");
        if (parts.length == 0){
            return null;
        }
        QuestionModel qm = new QuestionModel(Integer.valueOf(parts[0]),Integer.valueOf(parts[2]), parts[1].charAt(0));
        return qm;
    }

    @Override
    public void doCorrectGraphics() {
        view.setBackgroundColor(Color.GREEN);
    }

    @Override
    public void doIncorrectGraphics() {
        view.setBackgroundColor(Color.RED);
    }

    @Override
    public View getRootView() {
        return view;
    }
}
