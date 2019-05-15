package com.example.sanarproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Conteudo implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("titulo")
    @Expose
    private String title;
    @SerializedName("tipio")
    @Expose
    private String tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
