package com.example.midtermalik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.add_todo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Fragment toDoListFragment = new ToDoListFragment(generateTask());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.todo_list, toDoListFragment)
                .commitAllowingStateLoss();
    }



    public List<ToDo> generateTask() {
        String title = "title";
        String description = "description";
        String status = "status";
        String category = "category";
        Date duration = new Date();
        List<ToDo> tasks = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ToDo todo = new ToDo(
                    i,
                    title + i,
                    description + i,
                    status + i,
                    category + i,
                    duration
            );
            tasks.add(todo);
        }
        return tasks;
    }
}
