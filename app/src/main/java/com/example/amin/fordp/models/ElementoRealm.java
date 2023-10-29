package com.example.amin.fordp.models;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class ElementoRealm extends RealmObject {
    @PrimaryKey
    @Index
    private String title;
    private int imageResource;
    private String description;

    public ElementoRealm(){}
    public ElementoRealm(String title, int imageResource, String description) {
        this.title = title;
        this.imageResource = imageResource;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }
}