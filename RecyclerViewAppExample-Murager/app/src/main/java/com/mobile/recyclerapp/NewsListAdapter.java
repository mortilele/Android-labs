package com.mobile.recyclerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private List<News> newsList;

    @Nullable
    private ItemClickListener listener;

    public NewsListAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    public NewsListAdapter(List<News> newsList, @Nullable ItemClickListener listener) {
        this.newsList = newsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, null, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(params);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {
        final News news = newsList.get(position);
        holder.tvDate.setText(news.getDate());
        holder.tvAuthor.setText(news.getAuthor());
        holder.tvTitle.setText(news.getTitle());
        holder.tvTheme.setText(news.getTheme());
        holder.tvRatingCount.setText(String.valueOf(news.getRatingCount()));
        holder.tvComments.setText(String.valueOf(news.getCommentsCount()));
        holder.tvViews.setText(String.valueOf(news.getViewsCount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, news);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvTheme;
        TextView tvRatingCount;
        TextView tvViews;
        TextView tvComments;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvTheme = itemView.findViewById(R.id.tvTheme);
            tvRatingCount = itemView.findViewById(R.id.tvRating);
            tvViews = itemView.findViewById(R.id.tvViews);
            tvComments = itemView.findViewById(R.id.tvComments);
        }
    }

    interface ItemClickListener {
        void itemClick(int position, News item);
    }
}
