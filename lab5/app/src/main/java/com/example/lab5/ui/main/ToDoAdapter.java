package com.example.lab5.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.MyApplication;
import com.example.lab5.R;
import com.example.lab5.ToDo;
import com.example.lab5.room.database.CategoryDao;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private List<ToDo> tasks;
    private ToDoItemClickListener listener;
    private CategoryDao categoryDao = MyApplication.getInstance().getDatabase().categoryDao();


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
        holder.title.setText(todo.title);
        holder.status.setText(todo.status);
        String categoryTitle = "No category";
        try {
            categoryTitle = categoryDao.getCategoryById(todo.categoryId).title;
        }
        catch (Exception e) {
            Log.e("Failed load category", e.toString());
        }
        holder.category.setText(categoryTitle);
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
        TextView title;
        TextView status;
        TextView category;
        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.todo_item_title);
            status = itemView.findViewById(R.id.todo_item_status);
            category = itemView.findViewById(R.id.todo_item_category);
        }
    }

    public interface ToDoItemClickListener {
        void itemClick(int position, ToDo toDo);
    }
}