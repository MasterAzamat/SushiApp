package com.example.azaat.sushiapp;

/**
 * Created by Azaat on 10.04.2018.
 */

public class ListClass2 {
    private int id;
    private String title;
    private int price;
    private int img;
    private String weightSum;
    private String weight;
    private int count;

    public ListClass2(String title, int price, int img, int count,int id) {
        this.title = title;
        this.price = price;
        this.img = img;
        this.id = id;
//        this.weightSum = weightSum;
//        this.weight = weight;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getWeightSum() {
        return weightSum;
    }

    public void setWeightSum(String weightSum) {
        this.weightSum = weightSum;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }
}
