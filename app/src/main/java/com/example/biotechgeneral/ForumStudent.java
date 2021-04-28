package com.example.biotechgeneral;

public class ForumStudent {


    private String Quetion;
    private String Answer;
    private String FName;
    private String QuetionNo;

    public ForumStudent( String answer) {

        Answer = answer;

    }


    public ForumStudent(String fName, String quetion, String quetionNo) {
        FName = fName;
        Quetion = quetion;
        QuetionNo = quetionNo;

    }


    public String getQuetion() {
        return Quetion;
    }

    public void setQuetion(String quetion) {
        Quetion = quetion;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getQuetionNo() {
        return QuetionNo;
    }

    public void setQuetionNo(String quetionNo) {
        QuetionNo = quetionNo;
    }
}
