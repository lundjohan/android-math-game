package com.johanlund.mathgame;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.johanlund.mathgame.common.models.Level;
import com.johanlund.mathgame.common.models.LevelInfo;
import com.johanlund.mathgame.common.models.QuestionModel;
import com.johanlund.mathgame.main.MainActivity;
import com.johanlund.mathgame.questionsProducer.FakeQuestionsProducer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndToEndTest {
    ArrayList<Level> levels = new ArrayList<>();
    LevelInfo[] levelInfos;

    @Before
    public void initSomeLevels() {

        //levels
        QuestionModel[] questions = {
                new QuestionModel(1, 3, '+'),
                new QuestionModel(4, 2, '-')};
        Level one = new Level(questions, 1000);
        levels.add(one);

        //levelinfos
        levelInfos = new LevelInfo[]{new LevelInfo("easy", "first Level")};
    }


    @Test
    public void testAppFromStartToFinish() {

        //the members of this class are statically set.
        FakeQuestionsProducer fQp = new FakeQuestionsProducer(levels,levelInfos);


        //start
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);

        //First screen is the Welcome
        onView(withId(R.id.description)).check(matches(withText("first Level")));
        onView(withId(R.id.difficulty)).check(matches(withText("easy")));
        onView(withText("START GAME")).perform(click());

        //Now we should be in the view where the questions are.
        //question #1
        onView(withId(R.id.levelTitle)).check(matches(withText("Level 1")));
        onView(withId(R.id.mathQuestion)).check(matches(withText("1 + 3 =")));
        onView(withId(R.id.userAnswerInput)).perform(typeText("4"), closeSoftKeyboard());
        onView(withText("SEND")).perform(click());

        //question #2
        onView(withId(R.id.levelTitle)).check(matches(withText("Level 1")));
        onView(withId(R.id.mathQuestion)).check(matches(withText("4 - 2 =")));
        onView(withId(R.id.userAnswerInput)).perform(typeText("2"), closeSoftKeyboard());
        onView(withText("SEND")).perform(click());

        //There was only one level, and it is now completed so now we should be at the winning screen
        onView(withId(R.id.winTitle)).check(matches(withText("YOU WIN!")));
        onView(withText("NEW GAME")).perform(click());

        //we should now be back to the  Welcome Screen
        onView(withId(R.id.description)).check(matches(withText("first Level")));
        onView(withId(R.id.difficulty)).check(matches(withText("easy")));

        //close
        activityScenario.close();
    }
}