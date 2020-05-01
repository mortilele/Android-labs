package com.example.ocenika.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ocenika.R;
import com.example.ocenika.model.Comment;
import com.example.ocenika.util.StringUtils;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    public List<Comment> commentList;


    public CommentAdapter(@Nullable List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        final Comment comment = commentList.get(position);
        holder.dateView.setText(StringUtils.dateFormatted(comment.getCreated_at()));
        if (comment.getReview() != null && !comment.getReview().isEmpty()) {
            holder.textView.setText(comment.getReview());
        }

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView dateView;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.comment_item_text);
            dateView = itemView.findViewById(R.id.comment_item_date);
        }
    }
}