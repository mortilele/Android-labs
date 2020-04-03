package com.example.lab5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "todos",
        foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id")
)
public class ToDo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "category_id")
    public int categoryId;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "duration")
    public String duration;

    public ToDo() {
        this.status = "In process";
    }

    public ToDo(int categoryId) {
        this.status = "In process";
        this.categoryId = categoryId;
    }
}