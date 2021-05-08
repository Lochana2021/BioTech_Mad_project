package com.example.biotechgeneral;

public class ForumStudent  {




    private  String Answer;
    private  String  FName;
    private  String QuetionNo;

    public ForumStudent(String answer, String FName, String quetionNo) {
        Answer = answer;
        this.FName = FName;
        QuetionNo = quetionNo;
    }
    public ForumStudent(){

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
