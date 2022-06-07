package com.example.fitapp.model;

import android.net.Uri;

public class Exercise {
    private String Name;
    private String Description;
    private String Picture;
    private int Count;

    public Exercise() {
    }

    public Exercise(String name, String description, String picture) {
        Name = name;
        Description = description;
        Picture = picture;
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

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }
    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
