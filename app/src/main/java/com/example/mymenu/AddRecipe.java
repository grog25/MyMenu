package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddRecipe extends AppCompatActivity {

    private static final String TAG = "AddRecipe";
    List<String> ingredient = new ArrayList<String>();
    List<String> measurement = new ArrayList<String>();
    Recipe recipe = new Recipe();
    RecyclerView recyclerView;

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

        MyAdapter adapter = new MyAdapter(this, ingredient, measurement);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void saveRecipe() {
        EditText recipeName = (EditText) findViewById(R.id.recipeName);
        recipe.setRecipeName(recipeName.getText().toString());
    }

    public void nextActivity(View view){
        Intent intent = new Intent(this, activity_reicpe_instructions.class);
        startActivity(intent);
    }

}