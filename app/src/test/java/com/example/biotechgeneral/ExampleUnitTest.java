package com.example.biotechgeneral;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private sigle_type singleType;
    private QuizStudent quizStudent;

    @Before
    public void setUp(){
        singleType = new sigle_type();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void percentage_isCorrect(){
        float result = singleType.attPerCal(5);
        assertEquals(8.0,result,0.001);

    }

    @Before
    public void set_results(){
        quizStudent = new QuizStudent();
    }

    @Test
    public void calResult(){
        int result = quizStudent.result(3,2);
        assertEquals(1,result,0.001);
    }



}