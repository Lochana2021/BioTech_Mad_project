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


    /*Loch*/
    @Before
    public void setUp(){
        singleType = new sigle_type();
    }

    /*Achi*/
    @Before
    public void setPassListObj(){
        passListObj = new PassList();
    }

    @Before
    public  void setResponse(){response = new ForumResponse();}

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /*Loch*/
    @Test
    public void percentage_isCorrect(){
        float result = singleType.attPerCal(5);
        assertEquals(8.0,result,0.001);
    }

    /*Achi*/
    @Test
    public void passPercentage_isCorrect(){
        double passPercent = passListObj.calcPassPercentage(2 , 3);
        assertEquals(66.67, passPercent, 0.001);
    }


// Forum Test

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





}