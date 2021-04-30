package com.example.biotechgeneral;

public class Assignment {
    private String StdAssID;
    private Integer Week;
    private String Type;
    private String Date;
    private String Weather;
    private String Place;
    private String Description;
    private Integer StdMarksAss;

    public Assignment() {
    }

    public Integer getStdMarksAss() {
        return StdMarksAss;
    }

    public void setStdMarksAss(Integer stdMarksAss) {
        StdMarksAss = stdMarksAss;
    }

    public String getStdAssID() {
        return StdAssID;
    }

    public void setStdAssID(String stdAssID) {
        StdAssID = stdAssID;
    }

    public Integer getWeek() {
        return Week;
    }

    public void setWeek(Integer week) {
        Week = week;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWeather() {
        return Weather;
    }

    public void setWeather(String weather) {
        Weather = weather;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
