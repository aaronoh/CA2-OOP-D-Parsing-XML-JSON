package com.example.aaron.lab16aaronohare.model;

import android.graphics.Bitmap;

/**
 * Created by aaron on 22/02/2017.
 */
//Plain old java objects class -pojo
public class News {
    private String title;
    private String link;
    private String descreption;
    private String enlosureURL;
//Getters and Setters
    private Bitmap bitmap;

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public String getEnlosureURL() {
        return enlosureURL;
    }

    public void setEnlosureURL(String enlosureURL) {
        this.enlosureURL = enlosureURL;
    }
}
