package com.example.lab5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories", indices = {@Index(value = {"title"}, unique = true)})
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    public Category() {

    }

    public Category(String title) {
        this.title = title;
    }
}
