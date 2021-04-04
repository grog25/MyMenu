package com.example.mymenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    //Columns for the ingredients table
    public static final String INGREDIENT_TABLE = "INGREDIENT_TABLE";
    public static final String COLUMN_INGREDIENT = "INGREDIENT";
    public static final String COLUMN_INGREDIENT_ID = "INGREDIENT_ID";

    // Columns for the recipe table
    public static final String RECIPE_TABLE = "RECIPE_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_MEAL_NAME = "MEAL_NAME";
    public static final String COLUMN_MEASUREMENT_NUM = "MEASUREMENT_NUM";
    public static final String COLUMN_MEASUREMENT_FRAC = "MEASUREMENT_FRAC";
    public static final String COLUMN_MEASUREMENT_UNIT = "MEASUREMENT_UNIT";
    public static final String COLUMN_INGREDIENTS = COLUMN_INGREDIENT_ID;
    public static final String COLUMN_MEAL_TYPE = "MEAL_TYPE";
    public static final String COLUMN_INSTRUCTIONS = "INSTRUCTIONS";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "recipe.db", null, 1);
    }

    // this is called the first time a database is accessed. There should be code in here to
    // create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String recipeTable = "CREATE TABLE " + RECIPE_TABLE + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MEAL_NAME +
                " TEXT, " + COLUMN_MEASUREMENT_NUM + " INTEGER, " + COLUMN_MEASUREMENT_FRAC + " REAL, "
                + COLUMN_MEASUREMENT_UNIT + "TEXT," + COLUMN_INGREDIENTS + " INTEGER, " +
                COLUMN_MEAL_TYPE + "TEXT" + COLUMN_INSTRUCTIONS + "TEXT)";
        db.execSQL(recipeTable);

        String ingredientTable = "CREATE TABLE " + INGREDIENT_TABLE + " (" + COLUMN_INGREDIENT_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_INGREDIENT + "TEXT)";
        db.execSQL(ingredientTable);
    }

    // this is called if the database version number changes. It prevents previous users app from
    // breaking when you change database design.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + INGREDIENT_TABLE);
        onCreate(db);
    }

    public boolean addRecipe(RecipeModel recipeModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv1 = new ContentValues();

        cv1.put(COLUMN_MEAL_NAME, recipeModel.getName());
        cv1.put(COLUMN_MEASUREMENT_NUM, recipeModel.getMeasurement());
        cv1.put(COLUMN_INGREDIENT, recipeModel.getIngredient());
        cv1.put(COLUMN_MEAL_TYPE, recipeModel.isMealType());

        long insert1 = db.insert(RECIPE_TABLE, null, cv1);
        if (insert1 == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean addIngredient(Ingredients ingredients) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv2 = new ContentValues();

        cv2.put(COLUMN_INGREDIENT, ingredients.getIngredient());

        long insert2 = db.insert(INGREDIENT_TABLE, null, cv2);
        if (insert2 == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteOne(RecipeModel recipeModel) {
        // find customerModel in the database. If it is found, delete it and return true.
        // If not found, return false.

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + RECIPE_TABLE + " WHERE " + COLUMN_ID + " = " + recipeModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }

    }

    public List<RecipeModel> getEveryone() {

        List<RecipeModel> returnList = new ArrayList<>();

        // get data from the database

        String queryString = "SELECT * FROM " + RECIPE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        // Cursor is the result set, rawQuery returns a cursor.
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer object for each row.
            //Put them into the return list.
            do {
                int id = cursor.getInt(0);
                String mealName = cursor.getString(1);
                String measurements = cursor.getString(2);
                String ingredients = cursor.getString(3);
                boolean mealType = cursor.getInt(4) == 1;

                // creating a new recipe
                RecipeModel newRecipe = new RecipeModel(id, mealName, measurements, ingredients, mealType);
                // adding the recipe to the list
                returnList.add(newRecipe);

            } while (cursor.moveToNext());

        }
        else {
            // failure. do not add anything to the list. Leave it empty
        }

        // close both the cursor and the db when done
        cursor.close();
        db.close();
        return returnList;
    }
}*/

