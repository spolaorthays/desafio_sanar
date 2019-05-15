package com.example.sanarproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sanarproject.R;
import com.example.sanarproject.model.Conteudo;

import java.util.List;

public class RecyclerViewConteudoAdapter extends RecyclerView.Adapter<RecyclerViewConteudoAdapter.ViewHolder> {

    private List<Conteudo> conteudoList;

    public RecyclerViewConteudoAdapter(List<Conteudo> conteudoList) {
        this.conteudoList = conteudoList;
    }

    @NonNull
    @Override
    public RecyclerViewConteudoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_conteudo_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewConteudoAdapter.ViewHolder viewHolder, int position) {
        Conteudo conteudo = conteudoList.get(position);
        viewHolder.bind(conteudo);
    }

    @Override
    public int getItemCount() {
        return conteudoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private TextView tipo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.conteudo_titulo_id);
            tipo = itemView.findViewById(R.id.conteudo_tipo_id);
        }

        private void bind(Conteudo conteudo){
            titulo.setText(conteudo.getTitle());
            tipo.setText(conteudo.getTipo());
        }
    }


}
