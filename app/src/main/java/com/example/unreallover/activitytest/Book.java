package com.example.unreallover.activitytest;

/**
 * Created by Unreal Lover on 2017/7/8.
 */

public class Book {
    private String name;
    private int imageId;
    public Book(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;

    }
    public int getImageId() {
        return imageId;
    }
}
//Fruit -> Book