package com.example.lab5;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.example.lab5.room.database.AppDatabase;

public class MyApplication extends Application {
    public static MyApplication instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyApplication", "onCreate");
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "todo_db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        try {
//            create default category set
            database.categoryDao().insert(new Category("BackLog"));
            database.categoryDao().insert(new Category("Test"));
            database.categoryDao().insert(new Category("Merge request"));
            database.categoryDao().insert(new Category("Done"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }


}
