package com.example.biotechgeneral;

public class ForumQuetions {


    // Create Variables
    private String ForumName;
    private String QuetionNo;
    private String Quetion;

    // Create Constructor

    public ForumQuetions(String forumName, String quetionNo, String quetion) {
        ForumName = forumName;
        QuetionNo = quetionNo;
        Quetion = quetion;
    }

    // Create getters And setters

    public String getForumName() {
        return ForumName;
    }

    public void setForumName(String forumName) {
        ForumName = forumName;
    }

    public String getQuetionNo() {
        return QuetionNo;
    }

    public void setQuetionNo(String quetionNo) {
        QuetionNo = quetionNo;
    }

    public String getQuetion() {
        return Quetion;
    }

    public void setQuetion(String quetion) {
        Quetion = quetion;
    }
}
