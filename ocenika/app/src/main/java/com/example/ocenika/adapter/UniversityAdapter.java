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
import com.example.ocenika.model.UniversityList;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {
    public List<UniversityList> universityList;
    private universityItemClickListener listener;


    public UniversityAdapter(@Nullable List<UniversityList> universityLists, @Nullable universityItemClickListener listener) {
        this.universityList = universityLists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.university_item, parent, false);
        return new UniversityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityViewHolder holder, int position) {
        final UniversityList university = universityList.get(position);
        holder.nameView.setText(university.getName());
        Picasso.get()
                .load(university.getLogo())
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_image_black_24dp)
                .resize(200, 200)
                .into(holder.logoView);
        System.out.println(university);
    }

    @Override
    public int getItemCount() {
        return universityList.size();
    }

    public class UniversityViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        ImageView logoView;

        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.university_name);
            logoView = itemView.findViewById(R.id.university_logo);
        }
    }


    public interface universityItemClickListener {
        void itemClick(int position, UniversityList university);
    }
}
