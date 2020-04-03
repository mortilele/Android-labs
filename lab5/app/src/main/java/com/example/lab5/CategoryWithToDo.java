package com.example.lab5;

import androidx.room.Relation;

import java.util.List;

public class CategoryWithToDo {
    public int id;

    public String title;

    @Relation(parentColumn = "id", entityColumn = "category_id")
    public List<ToDo> toDoList;
}
