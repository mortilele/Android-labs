package com.example.lab5.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5.room.database.AppDatabase;
import com.example.lab5.MyApplication;
import com.example.lab5.R;
import com.example.lab5.ToDo;
import com.example.lab5.room.database.ToDoDao;

import java.util.List;

public class MainFragment extends Fragment {
    AppDatabase db;
    ToDoDao todoDao;
    List<ToDo> tasks;
    private ToDoAdapter.ToDoItemClickListener listener = null;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        db = MyApplication.getInstance().getDatabase();
        todoDao = db.todoDao();
        tasks = todoDao.getToDos();
        listener = new ToDoAdapter.ToDoItemClickListener() {
            @Override
            public void itemClick(int position, ToDo toDo) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, ToDoDetailFragment.newInstance(toDo))
                        .addToBackStack("second")
                        .commitAllowingStateLoss();
            }
        };
        ToDoAdapter adapter = new ToDoAdapter(tasks, listener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("alik", String.valueOf(tasks));
    }

}
