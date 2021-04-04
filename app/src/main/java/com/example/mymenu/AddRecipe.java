package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AddRecipe extends AppCompatActivity implements MyAdapter.OnRecipeListener {
    /**
     * This class is used to create a recipe object, which
     * includes the recipe name, ingredients, measurements, and
     * recipe description
     */
    private static final String TAG = "AddRecipe";
    public static final String INGREDIENTS = "com.example.MenuApp.INGREDIENTS";
    List<String> ingredient = new ArrayList<String>();
    List<String> measurement = new ArrayList<String>();
    Recipe recipe = new Recipe();
    RecyclerView recyclerView;


    private boolean clickBreakfast;
    private boolean clickLunch;
    private boolean clickDinner;
    private boolean clickDesert;
    private boolean clickSideDish;
    private boolean clickOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        ingredient.add("Ingredients");
        measurement.add("Measurements");

        recyclerView = findViewById(R.id.displayRecipe);
    }


    public void addIngredient(View view){
        Log.d(TAG, "add ingredient function called");

        EditText enterIngredient = (EditText) findViewById(R.id.enterIngredient);
        ingredient.add(enterIngredient.getText().toString());
        enterIngredient.setText("");

        EditText enterMeasurement = (EditText) findViewById(R.id.measurement);
        measurement.add(enterMeasurement.getText().toString());
        enterMeasurement.setText("");

        MyAdapter adapter = new MyAdapter(this, ingredient, measurement, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void saveRecipe() {
        EditText recipeName = (EditText) findViewById(R.id.recipeName);
        recipe.setDate(null);
        recipe.setRecipeName(recipeName.getText().toString());
        Gson gson = new Gson();
        String strIngredient = gson.toJson(ingredient);
        String strMeasurement = gson.toJson(measurement);
        recipe.setIngredient(strIngredient);
        recipe.setMeasurement(strMeasurement);
    }

    public void nextActivity(View view){
        saveRecipe();
        Gson gson = new Gson();
        String j = gson.toJson(recipe);

        Intent intent = new Intent(this, recipe_instructions.class);
        intent.putExtra(INGREDIENTS, j);

        startActivity(intent);
    }

    @Override
    public void onRecipeClick(int position) {

    }
}