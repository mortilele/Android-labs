package com.example.lab5.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Fragment;

import com.example.lab5.room.database.AppDatabase;
import com.example.lab5.room.database.CategoryDao;
import com.example.lab5.MyApplication;
import com.example.lab5.R;
import com.example.lab5.ToDo;
import com.example.lab5.room.database.ToDoDao;

@SuppressLint("ValidFragment")
public class AddToDoFragment extends Fragment {
    AppDatabase db;
    ToDoDao todoDao;
    CategoryDao categoryDao;


    public AddToDoFragment() {

    }


    public static AddToDoFragment newInstance() {
        return new AddToDoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);

        Button add = view.findViewById(R.id.add);
        final EditText titleEdit = view.findViewById(R.id.title);
        final EditText descriptionEdit = view.findViewById(R.id.description);
        final EditText categoryEdit = view.findViewById(R.id.categoryId);
        final EditText durationEdit = view.findViewById(R.id.duration);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEdit.getText().toString();
                String description = descriptionEdit.getText().toString();
                String duration = durationEdit.getText().toString();
                int category_id = Integer.parseInt(categoryEdit.getText().toString());
                ToDo todo = new ToDo();
                todo.title = title;
                todo.description = description;
                todo.duration = duration;
                try {
//                    Валидация того что такая категория существует
                    categoryDao.getCategoryById(category_id);
                    todo.categoryId = category_id;
                    todoDao.insert(todo);
                }
                catch (Exception e) {
                    Log.e("Failed to add:", e.toString());
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = MyApplication.getInstance().getDatabase();
        todoDao = db.todoDao();
        categoryDao = db.categoryDao();
    }

}
