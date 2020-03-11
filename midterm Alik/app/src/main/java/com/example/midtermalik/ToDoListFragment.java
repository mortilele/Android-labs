package com.example.midtermalik;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoListFragment extends Fragment {
    public List<ToDo> tasks;
    private ToDoAdapter.ToDoItemClickListener listener = null;

    public ToDoListFragment(List<ToDo> tasks) {
        this.tasks = tasks;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listener = new ToDoAdapter.ToDoItemClickListener() {
            @Override
            public void itemClick(int position, ToDo toDo) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.todo_list, ToDoDetailFragment.newInstance(toDo))
                        .addToBackStack("second")
                        .commitAllowingStateLoss();
            }
        };
        ToDoAdapter adapter = new ToDoAdapter(this.tasks, listener);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
