package com.example.unreallover.activitytest;

import android.util.Log;

/**
* Created by Unreal Lover on 2017/7/7.
*/
public class Fruit {
    private String name;
    private int imageId;
    public Fruit(String name, int imageId) {
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
