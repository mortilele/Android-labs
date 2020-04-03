package com.example.lab5;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {ToDo.class, Category.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ToDoDao todoDao();

    public abstract CategoryDao categoryDao();
}
