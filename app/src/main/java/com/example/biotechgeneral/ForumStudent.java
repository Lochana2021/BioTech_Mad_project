package com.example.biotechgeneral;

public class ForumStudent  {


//Declare Variables

    private  String Answer;
    private  String  FName;
    private  String QuetionNo;

    //Create constructor
    public ForumStudent(String answer, String FName, String quetionNo) {
        Answer = answer;
        this.FName = FName;
        QuetionNo = quetionNo;
    }

    // create default Constructor
    public ForumStudent(){

    }

    //create default constructor
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
