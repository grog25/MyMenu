package com.example.mymenu;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RunnableMethod implements Runnable{

    Recipe recipe = new Recipe();
    String method;
    List<Recipe> recipeList = new ArrayList<>();
    List<String> recipeNames = new ArrayList<>();
    Context context;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public List<String> getRecipeNames() {
        return recipeNames;
    }

    @Override
    public void run() {
        Log.d("Method: ", getMethod());
        if(getMethod() == "insert") {
            DatabaseClient.getInstance(context).getAppDatabase().recipeDao().insert(recipe);
            Log.d("Insert: ", String.valueOf(DatabaseClient.getInstance(context).getAppDatabase().recipeDao().getAll()));
        }

        else if(getMethod() == "getAll") {
            recipeList = DatabaseClient.getInstance(context).getAppDatabase().recipeDao().getAll();
            for (int i = 0; i < recipeList.size(); i++) {
                recipeNames.add(recipeList.get(i).getRecipeName());
            }
            Log.d("Crossing fingers: ", String.valueOf(recipeNames));
        }
    }
}
