package com.example.mymenu;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient mInstance;

    //our app database object
    private Recipe_Database recipeDatabase;

    DatabaseClient(Context mCtx) {
        this.mCtx = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        recipeDatabase = Room.databaseBuilder(mCtx, Recipe_Database.class, "MyToDos").build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mCtx);
        }
        return mInstance;
    }

    public Recipe_Database getAppDatabase() {
        return recipeDatabase;
    }
}
