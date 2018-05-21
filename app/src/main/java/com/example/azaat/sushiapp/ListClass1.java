package com.example.azaat.sushiapp;

/**
 * Created by Azaat on 10.04.2018.
 */

public class ListClass1 {
    private int id;
    private  String title;
    private int img;

    public ListClass1(String title, int img,int id) {
        this.title = title;
        this.img = img;
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }
}
