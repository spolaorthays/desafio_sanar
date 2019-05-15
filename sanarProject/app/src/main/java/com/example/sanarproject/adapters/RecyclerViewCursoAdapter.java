package com.example.sanarproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sanarproject.R;
import com.example.sanarproject.interfaces.RecyclerListenerCursos;
import com.example.sanarproject.model.Curso;

import java.util.List;

public class RecyclerViewCursoAdapter extends RecyclerView.Adapter<RecyclerViewCursoAdapter.ViewHolder> {

    private List<Curso> cursoList;
    private RecyclerListenerCursos listenerCursos;

    public RecyclerViewCursoAdapter(List<Curso> cursoList, RecyclerListenerCursos listenerCursos) {
        this.cursoList = cursoList;
        this.listenerCursos = listenerCursos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_curso_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Curso curso = cursoList.get(position);
        viewHolder.bind(curso);
    }


    @Override
    public int getItemCount() {
        return cursoList.size();
    }

    public void updateCursos(List<Curso> cursoList){
        this.cursoList = cursoList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageCurso;
        private TextView textName;
        private TextView textArea;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageCurso = itemView.findViewById(R.id.image_curso_id);
            textName = itemView.findViewById(R.id.name_curso_id);
            textArea = itemView.findViewById(R.id.area_curso_id);
            cardView = itemView.findViewById(R.id.card_curso_id);
        }

        public void bind (final Curso curso){
            if (curso.getImageURL() != null) {
                Glide.with(itemView.getContext())
                        .load(curso.getImageURL())
                        .placeholder(R.drawable.placeholder_image)
                        .into(imageCurso);
            }

            textName.setText(curso.getName());
            textArea.setText(curso.getArea());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerCursos.onCursoClicked(curso);
                }
            });
        }
    }
}
