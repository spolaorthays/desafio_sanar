package com.example.sanarproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Modulo implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nome")
    @Expose
    private String name;
    @Expose
    @SerializedName("conteudos")
    private List<Conteudo> conteudoList = null;

    public Modulo() {
    }

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

    public List<Conteudo> getConteudoList() {
        return conteudoList;
    }

    public void setConteudoList(List<Conteudo> conteudoList) {
        this.conteudoList = conteudoList;
    }

}
