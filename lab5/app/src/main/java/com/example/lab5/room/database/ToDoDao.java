package com.example.lab5.room.database;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab5.ToDo;

import java.util.List;

@Dao
public interface ToDoDao {

    @Query("SELECT * FROM todos")
    List<ToDo> getToDos();

    @Query("SELECT * FROM todos WHERE id = :id")
    ToDo getToDoById(int id);

    @Insert
    void insert(ToDo todo);

    @Update
    void update(ToDo toDo);

    @Delete
    void delete(ToDo toDo);
}
