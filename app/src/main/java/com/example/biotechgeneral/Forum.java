package com.example.biotechgeneral;

public class Forum  {


// Declare Variables
    private String Name;
    private String Description;
    private String Type;

    // Create constructor

    public Forum(String name, String description, String type) {
        Name = name;
        Description = description;
        Type = type;
    }

    // Create default constructor
    public Forum(){

    }

    // Create getters and setters

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}


