package com.example.azaat.sushiapp;

/**
 * Created by Azaat on 11.04.2018.
 */

public class BasketClass {

    private  int img;
    private String title;
    private  int count;
    private  int price;
    private int id;

    public BasketClass(int id,int img, String title, int count, int price) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.count = count;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
}
