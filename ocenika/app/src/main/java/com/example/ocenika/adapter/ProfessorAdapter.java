package com.example.ocenika.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ocenika.R;
import com.example.ocenika.model.Professor;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {
    public List<Professor> professor;
    private professorItemClickListener listener;


    public ProfessorAdapter(@Nullable List<Professor> universityLists, @Nullable professorItemClickListener listener) {
        this.professor = universityLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.professor_item, parent, false);
        return new ProfessorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        final Professor professor = this.professor.get(position);
        holder.nameView.setText(professor.getFull_name());
        holder.subjectsView.setText(professor.concatSubjects());
        holder.universitiesView.setText(professor.concatUniversities());
        Picasso.get()
                .load(professor.getAvatar())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_image_black_24dp)
                .resize(200, 200)
                .into(holder.avatarView);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.itemClick(position, professor.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return professor.size();
    }

    public class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        ImageView avatarView;
        TextView subjectsView;
        TextView universitiesView;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.professor_full_name);
            avatarView = itemView.findViewById(R.id.professor_avatar);
            subjectsView = itemView.findViewById(R.id.professor_subjects);
            universitiesView = itemView.findViewById(R.id.professor_universities);
        }
    }


    public interface professorItemClickListener {
        void itemClick(int position, int professorId);
    }
}