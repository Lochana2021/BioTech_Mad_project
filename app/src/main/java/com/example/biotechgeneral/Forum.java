package com.example.biotechgeneral;

import android.widget.ImageView;

public class Forum {

    private String Name;
    private String Description;
    private String Type;

    private ImageView image;

    public Forum(ImageView image) {
        this.image = image;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Forum() {

    }

    public Forum(String name, String description, String type) {
        Name = name;
        Description = description;
        Type = type;
    }


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


