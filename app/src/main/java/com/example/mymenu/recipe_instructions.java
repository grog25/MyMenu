package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class recipe_instructions extends AppCompatActivity implements MyAdapter.OnRecipeListener {
    RecyclerView recyclerView2;
    public List<String> ingredient;
    public List<String> measurement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);

        Gson gson = new Gson();
        Intent intent = getIntent();

        String ingredients = intent.getStringExtra(AddRecipe.INGREDIENTS);
        Recipe recipe = gson.fromJson(ingredients, Recipe.class);

        ingredient = gson.fromJson(recipe.getIngredient(), new TypeToken<ArrayList<String>>(){}.getType());
        measurement = gson.fromJson(recipe.getMeasurement(), new TypeToken<ArrayList<String>>(){}.getType());


        recyclerView2 = findViewById(R.id.recyclerView2);
        MyAdapter adapter2 = new MyAdapter(this, ingredient, measurement, this);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


    }

    public void saveRecipeInstructions(View view) throws InterruptedException {
        Gson gson = new Gson();
        Intent intent = getIntent();
        String ingredients = intent.getStringExtra(AddRecipe.INGREDIENTS);
        Recipe recipe = gson.fromJson(ingredients, Recipe.class);
        EditText recipeInstructions = (EditText) findViewById(R.id.recipeInstructions);
        String myInstructions = recipeInstructions.getText().toString();
        recipe.setInstructions(myInstructions);

        RunnableMethod runnableMethod = new RunnableMethod();
        runnableMethod.setRecipe(recipe);
        runnableMethod.setContext(getApplicationContext());
        runnableMethod.setMethod("insert");
        Thread thread1 = new Thread(runnableMethod);
        thread1.start();
        thread1.join();

        runnableMethod.setMethod("getAll");
        Thread thread2 = new Thread(runnableMethod);
        thread2.start();
        thread2.join();
    }

    @Override
    public void onRecipeClick(int position) {

    }
}