package com.example.lab7;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorite {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String id;

    public Favorite() {

    }

    public Favorite(String id) {
        this.id = id;
    }
}
