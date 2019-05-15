package com.example.sanarproject.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Curso implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("nome")
    private String name;
    @SerializedName("imagem")
    private String imageURL;
    @SerializedName("area")
    private String area;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
