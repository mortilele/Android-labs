package com.example.lab7;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    AppDatabase db;
    FavoriteDao favoriteDao;
    List<Favorite> favorites;
    public List<Job> jobs;
    private jobItemClickListener listener;


    public JobAdapter(@Nullable List<Job> jobs, @Nullable jobItemClickListener listener) {
        this.jobs = jobs;
        this.listener = listener;
        db = MyApplication.getInstance().getDatabase();
        favoriteDao = db.favoriteDao();
        favorites =  favoriteDao.getFavorites();
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, final int position) {
        final Job job = jobs.get(position);
        boolean is_favorite = false;
        holder.id = job.getId();
        holder.title.setText(job.getTitle());
        holder.type.setText(job.getType());
        holder.created_at.setText(job.getCreated_at());
        for (Favorite favorite : favorites) {
            if (job.getId().equals(favorite.id)) {
                is_favorite = true;
                holder.like.setImageResource(R.drawable.ic_favorite_black_24dp);
            }
        }
        Picasso.get()
                .load(job.getCompany_logo())
                .placeholder(R.drawable.ic_business_center_black_24dp)
                .error(R.drawable.ic_business_center_black_24dp)
                .resize(200, 200)
                .into(holder.company_logo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, job);
                }
            }
        });
        boolean finalIs_favorite = is_favorite;
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalIs_favorite == true) {
                    holder.like.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    favoriteDao.delete(new Favorite(job.getId()));
                } else {
                    holder.like.setImageResource(R.drawable.ic_favorite_black_24dp);
                    favoriteDao.insert(new Favorite(job.getId()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView type;
        TextView created_at;
        ImageView company_logo;
        ImageView like;
        String id;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.job_title);
            type = itemView.findViewById(R.id.type);
            created_at = itemView.findViewById(R.id.created_at);
            company_logo = itemView.findViewById(R.id.company_logo);
            like = itemView.findViewById(R.id.like);
        }
    }

    public interface jobItemClickListener {
        void itemClick(int position, Job job);
    }
}