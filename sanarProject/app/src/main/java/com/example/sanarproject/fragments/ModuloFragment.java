package com.example.sanarproject.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sanarproject.MainActivity;
import com.example.sanarproject.R;
import com.example.sanarproject.adapters.RecyclerViewModuloAdapter;
import com.example.sanarproject.dao.Dao;
import com.example.sanarproject.interfaces.CursoListenerDetail;
import com.example.sanarproject.interfaces.ModuloListenerDetail;
import com.example.sanarproject.interfaces.RecyclerListenerModulos;
import com.example.sanarproject.model.Curso;
import com.example.sanarproject.model.Modulo;
import com.example.sanarproject.service.ServiceListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModuloFragment extends Fragment implements ServiceListener, RecyclerListenerModulos {

    private CursoListenerDetail cursoListenerDetail;
    private Curso curso;
    private RecyclerViewModuloAdapter adapter;
    private RecyclerView recyclerView;
    private List<Modulo> moduloList;
    private Button btVoltarAosCursos;
    private ModuloListenerDetail moduloListenerDetail;
    private ProgressBar progressBar;

    public ModuloFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Comunição curso para modulo
        this.cursoListenerDetail = (CursoListenerDetail) context;
        this.curso = (Curso)getArguments().getSerializable(MainActivity.OBJ_CURSO);
        //Comunicação modulo para moduloDetail
        this.moduloListenerDetail = (ModuloListenerDetail) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modulo, container, false);

        progressBar = view.findViewById(R.id.progressbar_modulo_id);
        btVoltarAosCursos = view.findViewById(R.id.button_return_modulo_id);

        progressBar.setVisibility(View.VISIBLE);
        btVoltarAosCursos.setVisibility(View.INVISIBLE);

        recyclerView = view.findViewById(R.id.recycler_modulo_id);

        Dao dao = new Dao();
        dao.getModuloList(getContext(), this);

        setVoltarAosCursos();

        return view;
    }

    private void setVoltarAosCursos(){
        btVoltarAosCursos.setOnClickListener(new View.OnClickListener() {
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
        moduloList = (List<Modulo>) object;

        adapter = new RecyclerViewModuloAdapter(moduloList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar.setVisibility(View.GONE);
        btVoltarAosCursos.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(),"Error: "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onModuloClicked(Modulo modulo) {
        moduloListenerDetail.iniciarFragmentModuloDetail(modulo);
    }
}
