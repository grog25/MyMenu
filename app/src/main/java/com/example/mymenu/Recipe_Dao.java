package com.example.mymenu;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface Recipe_Dao {

    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Insert
    void insert(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Update
    void update(Recipe recipe);

}
