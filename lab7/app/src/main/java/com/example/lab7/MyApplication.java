package com.example.lab7;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;


public class MyApplication extends Application {
    public static MyApplication instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication", "onCreate");
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "favorites_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
//        try {
//            database.favoriteDao().insert(new Favorite("90cc0d62-ebfa-482a-a195-59afeb958000"));
//            database.favoriteDao().insert(new Favorite("631d654c-0782-4d9c-b454-512725069274"));
//        }
//        catch (Exception e) {
//            System.out.println(e.toString());
//        }

    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }


}