package com.example.lab5.room.database;

import androidx.room.Relation;

import com.example.lab5.ToDo;

import java.util.List;

public class CategoryWithToDo {
    public int id;

    public String title;

    @Relation(parentColumn = "id", entityColumn = "category_id")
    public List<ToDo> toDoList;
}
