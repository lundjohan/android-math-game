package com.johanlund.mathgame.questionanswer;

import com.johanlund.mathgame.common.ViewMvc;

public interface AnswerQuestionViewMvc extends ViewMvc {
    void bindQuestionToView(QuestionModel qm);
    void doCorrectGraphics();
    void doIncorrectGraphics();
    QuestionModel retrieveQuestionFromView();
    Integer retrieveAnswer();


    interface Listener extends ViewMvc.Listener{
        public void checkAnswer();
    }
}
