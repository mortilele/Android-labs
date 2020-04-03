package com.example.lab5;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ColumnInfo.TEXT;

@Entity(tableName = "todos",
        foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id"),
        indices = {@Index("title")}
)

public class ToDo {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String phone;

    @ColumnInfo(name = "category_id")
    public int CategoryId;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "duration")
    public Date duration;
}