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

    private List<Job> jobs;
    private jobItemClickListener listener;

    public JobAdapter(List<Job> jobs, @Nullable jobItemClickListener listener) {
        this.jobs = jobs;
        this.listener = listener;
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
        holder.title.setText(job.getTitle());
        holder.type.setText(job.getType());
        holder.created_at.setText(job.getCreated_at());
        Picasso.get()
                .load(job.getCompany_logo())
                .placeholder(R.drawable.ic_business_center_black_24dp)
                .error(R.drawable.ic_business_center_black_24dp)
                .resize(200, 200)
                .into(holder.company_logo);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, job);
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