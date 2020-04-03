package com.example.lab5.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import com.example.lab5.room.database.CategoryDao;
import com.example.lab5.MyApplication;
import com.example.lab5.R;
import com.example.lab5.ToDo;
import com.example.lab5.room.database.ToDoDao;


public class ToDoDetailFragment  extends Fragment {

    public TextView id;
    public EditText title;
    public EditText description;
    public EditText status;
    public TextView category;
    public EditText duration;
    private CategoryDao categoryDao = MyApplication.getInstance().getDatabase().categoryDao();
    private ToDoDao toDoDao = MyApplication.getInstance().getDatabase().todoDao();

    public ToDoDetailFragment() {

    }

    public static ToDoDetailFragment newInstance(ToDo todo) {
        ToDoDetailFragment fragment = new ToDoDetailFragment();
        Bundle data = new Bundle();
        data.putString("id", todo.id + "");
        data.putString("title", todo.title);
        data.putString("description", todo.description);
        data.putString("status", todo.status);
        data.putString("duration", todo.duration);
        data.putInt("category", todo.categoryId);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_detail, container, false);
        final Button update = view.findViewById(R.id.todo_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int todo_id = Integer.parseInt(id.getText().toString());
                ToDo todo = toDoDao.getToDoById(todo_id);
                todo.title = title.getText().toString();
                todo.status = status.getText().toString();
                todo.description = description.getText().toString();
                todo.duration = duration.getText().toString();
                toDoDao.update(todo);
            }
        });

        final Button delete = view.findViewById(R.id.todo_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int todo_id = Integer.parseInt(id.getText().toString());
                ToDo todo = toDoDao.getToDoById(todo_id);
                toDoDao.delete(todo);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = view.findViewById(R.id.todo_detail_id);
        title = view.findViewById(R.id.todo_detail_title);
        description = view.findViewById(R.id.todo_detail_description);
        status = view.findViewById(R.id.todo_detail_status);
        category = view.findViewById(R.id.todo_detail_category);
        duration = view.findViewById(R.id.todo_detail_duration);
        try {
            id.setText(getArguments().getString("id"));
            title.setText(getArguments().getString("title"));
            description.setText(getArguments().getString("description"));
            status.setText(getArguments().getString("status"));
            duration.setText(getArguments().getString("duration"));
            try {
                int category_id = getArguments().getInt("category");
                category.setText(categoryDao.getCategoryById(category_id).title);
            }
            catch (Exception e) {
                category.setText("No category");
            }
        }
        catch (Exception e) {
            Log.e("Error", e + " ");
        }

    }
}