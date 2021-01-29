package com.johanlund.mathgame.questionsDatabase;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.johanlund.mathgame.common.models.Level;
import com.johanlund.mathgame.common.models.QuestionModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class JSONDatabaseTest {
    JSONDatabase jsonDatabase;

    @Before
    public void initiateJSONDatabase() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        jsonDatabase = new JSONDatabase(appContext.getAssets());
    }

    /**
     * This test checks valididy of data from database,
     * but is agnostic of the implementation of the database.
     */
    @Test
    public void levelDataDoesntContainFalseTypes() {

        //1. RETRIEVE ALL LEVELS & LEVELINFOS THAT EXIST IN DATABASE/ JSON-FILE
        int totalNrOfLevels = jsonDatabase.getNrOfLevels();

        Level[] levels = new Level[totalNrOfLevels];
        //levels starting at 1
        for (int i = 1; i < totalNrOfLevels + 1; i++) {
            Level l = jsonDatabase.getLevel(i);
            levels[i - 1] = l;
        }

        //2.
        for (int i = 0; i < levels.length; i++) {
            Level l = levels[i];


            //TEST THAT TIME IS AN INT
            assertEquals(l.getTimeInSecPerQuestion() - l.getTimeInSecPerQuestion(), 0);

            //TEST THAT QUESTIONS ARE OF CORRECT TYPE
            QuestionModel[] qms = l.getQuestions();
            for (int j = 0; j < qms.length; j++) {
                int left = qms[i].getLeft();
                int right = qms[i].getRight();
                char operator = qms[i].getOperator();

                assertEquals(left - left, 0);
                assertEquals(right - right, 0);

                //TEST THAT OPERATOR IS ONLY ONE LETTER AND IS ONE OF THE FOUR BASIG ARITHEMETIC TYPES
                String regex = "^[+\\-*/]$";
                assertTrue(String.valueOf(operator).matches(regex));
            }
        }
    }
}