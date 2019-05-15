package com.example.sanarproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.sanarproject.fragments.CursoFragment;
import com.example.sanarproject.fragments.ModuloDetailFragment;
import com.example.sanarproject.fragments.ModuloFragment;
import com.example.sanarproject.interfaces.CursoListenerDetail;
import com.example.sanarproject.interfaces.ModuloListenerDetail;
import com.example.sanarproject.model.Curso;
import com.example.sanarproject.model.Modulo;

public class MainActivity extends AppCompatActivity implements CursoListenerDetail, ModuloListenerDetail {

    public static final String OBJ_CURSO = "curso";
    public static final String OBJ_MODULO = "modulo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replaceFragment(new CursoFragment());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_main_activity, fragment);
        transaction.commit();
    }

    @Override
    public void iniciarFragmentModulos(Curso curso) {
        ModuloFragment moduloFragment = new ModuloFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(OBJ_CURSO, curso);
        moduloFragment.setArguments(bundle);

        replaceFragment(moduloFragment);
    }

    @Override
    public void iniciarFragmentModuloDetail(Modulo modulo) {
        ModuloDetailFragment detailFragment = new ModuloDetailFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(OBJ_MODULO, modulo);
        detailFragment.setArguments(bundle);

        replaceFragment(detailFragment);
    }
}
