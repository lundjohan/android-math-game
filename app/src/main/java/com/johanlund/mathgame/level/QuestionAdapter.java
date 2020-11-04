package com.johanlund.mathgame.level;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.johanlund.mathgame.common.QuestionModel;
import com.johanlund.mathgame.questionanswer.AnswerQuestionFragment;

import java.util.ArrayList;
import java.util.List;

import static com.johanlund.mathgame.common.Constants.POSITION_IN_ADAPTER;
import static com.johanlund.mathgame.common.Constants.QUESTION_MODEL;

//package private
class QuestionAdapter extends FragmentStateAdapter {
    ArrayList<QuestionModel> qms;

    /**
     * @param fragment
     * @param qms      MUST be ArrayList (so elements can be removed inside)
     */
    public QuestionAdapter(@NonNull Fragment fragment, ArrayList<QuestionModel> qms) {
        super(fragment);
        this.qms = qms;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new AnswerQuestionFragment();
        Bundle args = new Bundle();

        //this is wrong
        args.putInt(POSITION_IN_ADAPTER, position);
        args.putParcelable(QUESTION_MODEL, qms.get(position));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return qms.size();
    }

    /*Why Override this function?
        => See comments for this function in FragmentStateAdapter*/
    @Override
    public long getItemId(int pos) {
        return qms.get(pos).hashCode();
    }

    /*Why Override this function?
    => See comments for this function in FragmentStateAdapter*/
    @Override
    public boolean containsItem(long itemId) {
        for (int pos = 0; pos < qms.size(); pos++) {
            if (qms.get(pos).hashCode() == itemId) {
                return true;
            }
        }
        return false;
    }
    ArrayList<QuestionModel> getQuestionModels(){
        return qms;
    }

    /**
     * @param strOfQuestionModel should be the strOfQuestionModel equivalent of qms.get(pos).hashCode(),
     *                           where pos is the location of the fragment in ViewPager2.
     *                           <p>
     *                           Since position will be altered -1 for the Fragments with place > pos,
     *                           you cannot simple use pos as parameter and then qms.remove(pos).
     *                           <p>
     *                           see: https://stackoverflow.com/questions/57938930/remove-fragment-in-viewpager2-use-fragmentstateadapter-but-still-display
     */
    void popQuestion(String strOfQuestionModel) {
        for (int pos = 0; pos < qms.size(); pos++) {
            String str = qms.get(pos).toString();
            if (str.equals(strOfQuestionModel)) {
                qms.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, getItemCount());
                break;
            }
        }
    }
}
