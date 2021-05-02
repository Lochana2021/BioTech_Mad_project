package com.example.biotechgeneral;

public class ForumQuetions {

    private String ForumName;
    private String QuetionNo;
    private String Quetion;

    public ForumQuetions(String forumName, String quetionNo, String quetion) {
        ForumName = forumName;
        QuetionNo = quetionNo;
        Quetion = quetion;
    }

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
