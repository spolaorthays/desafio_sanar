package com.example.sanarproject.dao;

import android.content.Context;

import com.example.sanarproject.model.Curso;
import com.example.sanarproject.model.Modulo;
import com.example.sanarproject.service.RetrofitService;
import com.example.sanarproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dao {

    //Cursos
    public List<Curso> getCursoList (Context context, final ServiceListener listener){

    Call<List<Curso>> call = RetrofitService.getApi().getCursos();

    call.enqueue(new Callback<List<Curso>>() {
        @Override
        public void onResponse(Call<List<Curso>> call, Response<List<Curso>> response) {
            if (response.body() != null){
                listener.onSuccess(response.body());
            }
        }

        @Override
        public void onFailure(Call<List<Curso>> call, Throwable t) {
            listener.onError(t);
        }
    });

        return new ArrayList<>();
    }

    //Modulos e conteúdos
    public List<Modulo> getModuloList (Context context, final ServiceListener listener){

        Call<List<Modulo>> call = RetrofitService.getApi().getModulos();

        call.enqueue(new Callback<List<Modulo>>() {
            @Override
            public void onResponse(Call<List<Modulo>> call, Response<List<Modulo>> response) {
                if (response.body() != null){
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Modulo>> call, Throwable t) {
                listener.onError(t);
            }
        });

        return new ArrayList<>();
    }

}
