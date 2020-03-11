package com.example.midtermalik;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private List<ToDo> tasks;
    private ToDoItemClickListener listener;


    public ToDoAdapter(List<ToDo> tasks, @Nullable ToDoItemClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, final int position) {
        final ToDo todo = tasks.get(position);
        Log.e("Recyc", todo.id + " ");
        holder.id.setText(todo.id + "");
        holder.title.setText(todo.title);
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        holder.durations.setText(df.format(todo.duration));
        holder.status.setText(todo.status);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.itemClick(position, todo);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView status;
        TextView durations;
        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.todo_item_id);
            title = itemView.findViewById(R.id.todo_item_title);
            status = itemView.findViewById(R.id.todo_item_status);
            durations= itemView.findViewById(R.id.todo_item_durations);
        }
    }

    interface ToDoItemClickListener {
        void itemClick(int position, ToDo toDo);
    }
}