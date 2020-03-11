package com.example.midtermalik;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ToDoDetailFragment  extends Fragment {

    private TextView id;
    private TextView title;
    private TextView description;
    private TextView status;
    private TextView category;
    private TextView durations;

    public ToDoDetailFragment() {

    }

    public static ToDoDetailFragment newInstance(ToDo todo) {
        ToDoDetailFragment fragment = new ToDoDetailFragment();
        Bundle data = new Bundle();
        data.putString("id", todo.id + "");
        data.putString("title", todo.title);
        data.putString("description", todo.description);
        data.putString("status", todo.status);
        data.putString("category", todo.category);
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        data.putString("duration", df.format(todo.duration));
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.todo_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = view.findViewById(R.id.todo_detail_id);
        title = view.findViewById(R.id.todo_detail_title);
        description = view.findViewById(R.id.todo_detail_description);
        status = view.findViewById(R.id.todo_detail_status);
        category = view.findViewById(R.id.todo_detail_category);
        durations = view.findViewById(R.id.todo_detail_durations);
        try {
            id.setText(getArguments().getString("id"));
            title.setText(getArguments().getString("title"));
            description.setText(getArguments().getString("description"));
            status.setText(getArguments().getString("status"));
            category.setText(getArguments().getString("category"));
            durations.setText(getArguments().getString("duration"));
        }
        catch (Exception e) {
            Log.e("Error", e + " ");
        }

    }
}
