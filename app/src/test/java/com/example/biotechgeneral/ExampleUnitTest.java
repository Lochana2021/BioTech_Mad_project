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
    private PassList passListObj;
    private ForumResponse response;
    private QuizStudent quizStudent;


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //IT19971490 Wijerathna L.M.
    @Before
    public void setUp(){
        singleType = new sigle_type();
    }

    @Test
    public void percentage_isCorrect_1(){
        float result = singleType.attPerCal(5);
        assertEquals(8.0,result,0.001);
    }

    @Test
    public void percentage_isCorrect_2(){
        float result = singleType.attPerCal(7);
        assertEquals(12.0,result,0.001);
    }

    //IT19975528 Hewawitharana G.H.A.U.
    @Before
    public void setPassListObj(){
        passListObj = new PassList();
    }

    @Test
    public void passPercentage_isCorrect_1(){
        double passPercent = passListObj.calcPassPercentage(2 , 3);
        assertEquals(66.67, passPercent, 0.001);
    }

    @Test
    public void passPercentage_isCorrect_2(){
        double passPercent = passListObj.calcPassPercentage(3 , 5);
        assertEquals(60.00, passPercent, 0.001);
    }

    //IT19013138 Fernando B. A. M.
    @Before
    public  void setResponse(){response = new ForumResponse();}


    @Test
    public void calcResponsStudent(){
        double passPercent = response.calcResponse(10 , 20);
        assertEquals(50.00, passPercent, 0.001);
    }

    @Test
    public void calcRespons_Student(){
        double passPercent = response.calcResponse(2 , 20);
        assertEquals(10.00, passPercent, 0.001);
    }

    //IT199713302 Kodisinghe K. A. D. V.
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