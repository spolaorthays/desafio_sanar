package com.example.sanarproject.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sanarproject.MainActivity;
import com.example.sanarproject.R;
import com.example.sanarproject.adapters.RecyclerViewCursoAdapter;
import com.example.sanarproject.dao.Dao;
import com.example.sanarproject.interfaces.CursoListenerDetail;
import com.example.sanarproject.interfaces.RecyclerListenerCursos;
import com.example.sanarproject.model.Curso;
import com.example.sanarproject.model.Modulo;
import com.example.sanarproject.service.ServiceListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursoFragment extends Fragment implements ServiceListener, RecyclerListenerCursos {

    private List<Curso> cursoList;
    private RecyclerViewCursoAdapter adapter;
    private RecyclerView recyclerView;
    private CursoListenerDetail listenerDetail;
    private ProgressBar progressBar;

    public CursoFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listenerDetail = (CursoListenerDetail) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_curso, container, false);

        progressBar = view.findViewById(R.id.progressbar_curso_idd);
        progressBar.setVisibility(View.VISIBLE);

        setupRecyclerView(view);

        return view;
    }

    private void setupRecyclerView(View view){
        recyclerView = view.findViewById(R.id.recycler_curso_fragment);

        Dao dao = new Dao();

        adapter = new RecyclerViewCursoAdapter(dao.getCursoList(getContext(),this), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public void onSuccess(Object object) {
        cursoList = (List<Curso>) object;
        adapter.updateCursos(cursoList);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(getContext(),"Error: "+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCursoClicked(Curso curso) {
        listenerDetail.iniciarFragmentModulos(curso);

    }
}
