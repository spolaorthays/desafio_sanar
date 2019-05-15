package com.example.sanarproject.service;

import com.example.sanarproject.model.Curso;
import com.example.sanarproject.model.Modulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/v1/cursos")
    Call<List<Curso>> getCursos();

    @GET("/v1/modulos")
    Call<List<Modulo>> getModulos();

}
