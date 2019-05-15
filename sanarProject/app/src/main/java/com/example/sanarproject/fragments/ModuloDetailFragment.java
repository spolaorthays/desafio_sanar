package com.example.sanarproject.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sanarproject.MainActivity;
import com.example.sanarproject.R;
import com.example.sanarproject.adapters.RecyclerViewConteudoAdapter;
import com.example.sanarproject.dao.Dao;
import com.example.sanarproject.interfaces.ModuloListenerDetail;
import com.example.sanarproject.model.Conteudo;
import com.example.sanarproject.model.Curso;
import com.example.sanarproject.model.Modulo;
import com.example.sanarproject.service.ServiceListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModuloDetailFragment extends Fragment implements ServiceListener {

    private RecyclerView recyclerView;
    private RecyclerViewConteudoAdapter adapter;
    private ModuloListenerDetail moduloListenerDetail;
    private Button buttonBack;
    private ProgressBar progressBar;

    private Modulo modulo;

    public ModuloDetailFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.moduloListenerDetail = (ModuloListenerDetail) context;
        this.modulo = (Modulo) getArguments().getSerializable(MainActivity.OBJ_MODULO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modulo_detail, container, false);

        progressBar = view.findViewById(R.id.progressbar_conteudo_id);
        buttonBack = view.findViewById(R.id.button_detail_voltar_id);

        progressBar.setVisibility(View.VISIBLE);
        buttonBack.setVisibility(View.INVISIBLE);

        recyclerView = view.findViewById(R.id.recycler_conteudo_id);

        Dao dao = new Dao();

        dao.getModuloList(getContext(),this);

        setVoltarAosModulos();

        return view;
    }

    private void setVoltarAosModulos(){
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container_main_activity, new CursoFragment());
                transaction.commit();
            }
        });
    }

    @Override
    public void onSuccess(Object object) {
        List<Modulo> modList = (List<Modulo>) object;
        //Pega o id selecionado do bundle
        int id = modulo.getId();
        //Seleciona apenas o Modulo clickado
        Modulo modSelecionado = new Modulo();

        for (Modulo mod : modList){
            if(mod.getId() == id){
                modSelecionado = mod;
                break;
            }
        }
        List<Conteudo> conteudo = modSelecionado.getConteudoList();
        adapter = new RecyclerViewConteudoAdapter(conteudo);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar.setVisibility(View.GONE);
        buttonBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(),"Error: "+throwable.getMessage(), Toast.LENGTH_LONG).show();
    }
}
