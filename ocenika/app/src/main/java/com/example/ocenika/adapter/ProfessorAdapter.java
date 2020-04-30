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
import com.example.ocenika.model.ProfessorList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {
    public List<ProfessorList> professorList;
    private professorItemClickListener listener;


    public ProfessorAdapter(@Nullable List<ProfessorList> universityLists, @Nullable professorItemClickListener listener) {
        this.professorList = universityLists;
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
        final ProfessorList professor = professorList.get(position);
        holder.nameView.setText(professor.getFull_name());
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
        return professorList.size();
    }

    public class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        ImageView avatarView;

        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.professor_full_name);
            avatarView = itemView.findViewById(R.id.professor_avatar);
        }
    }


    public interface professorItemClickListener {
        void itemClick(int position, int professorId);
    }
}