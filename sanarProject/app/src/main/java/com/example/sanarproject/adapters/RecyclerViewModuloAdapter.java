package com.example.sanarproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanarproject.R;
import com.example.sanarproject.interfaces.RecyclerListenerModulos;
import com.example.sanarproject.model.Modulo;

import java.util.List;

public class RecyclerViewModuloAdapter extends RecyclerView.Adapter<RecyclerViewModuloAdapter.ViewHolder> {

    private List<Modulo> moduloList;
    private RecyclerListenerModulos listenerModulos;

    public RecyclerViewModuloAdapter(List<Modulo> moduloList, RecyclerListenerModulos listenerModulos) {
        this.moduloList = moduloList;
        this.listenerModulos = listenerModulos;
    }

    @NonNull
    @Override
    public RecyclerViewModuloAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_modulo_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewModuloAdapter.ViewHolder viewHolder, int position) {
        Modulo modulo = moduloList.get(position);
        viewHolder.bind(modulo);
    }

    @Override
    public int getItemCount() {
        return moduloList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nome;
        private ImageView imageAvancar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            nome = itemView.findViewById(R.id.name_modulo_id);
            imageAvancar = itemView.findViewById(R.id.imagem_modulo_id);
        }

        private void bind(final Modulo modulo){
            nome.setText(modulo.getName());

            imageAvancar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerModulos.onModuloClicked(modulo);
                }
            });

        }
    }
}
