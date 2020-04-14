package com.example.lab7;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    List<Favorite> getFavorites();

    @Insert
    void insert(Favorite favorite);

    @Delete
    void delete(Favorite favorite);
}
