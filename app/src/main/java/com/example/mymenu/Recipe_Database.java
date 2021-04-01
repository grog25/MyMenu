package com.example.mymenu;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

    @Database(entities = {Recipe.class}, version = 1)
    public abstract class Recipe_Database extends RoomDatabase {
        public abstract Recipe_Dao recipeDao();
    }

