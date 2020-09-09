package com.johanlund.mathgame.questionanswer;

import com.johanlund.mathgame.common.ViewMvc;

public interface AnswerQuestionViewMvc extends ViewMvc {
    public void bindQuestionToView(QuestionModel qm);
    public QuestionModel retrieveQuestionFromView();
}
