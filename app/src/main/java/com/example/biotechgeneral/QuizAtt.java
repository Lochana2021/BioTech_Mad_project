package com.example.biotechgeneral;

public class QuizAtt {
    private String QName;
    private String QRegNum;
    private String QID;
    private String Qdate;
    private String results;

    public QuizAtt() {
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getQName() {
        return QName;
    }

    public void setQName(String QName) {
        this.QName = QName;
    }

    public String getQRegNum() {
        return QRegNum;
    }

    public void setQRegNum(String QRegNum) {
        this.QRegNum = QRegNum;
    }

    public String getQID() {
        return QID;
    }

    public void setQID(String QID) {
        this.QID = QID;
    }

    public String getQdate() {
        return Qdate;
    }

    public void setQdate(String qdate) {
        Qdate = qdate;
    }
}
