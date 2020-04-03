package com.example.lab5.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab5.Category;
import com.example.lab5.ToDo;


@Database(entities = {ToDo.class, Category.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ToDoDao todoDao();

    public abstract CategoryDao categoryDao();
}
