package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AddRecipe extends AppCompatActivity {

    private static final String TAG = "AddRecipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }

    public void addIngredient(View view){
        Log.d(TAG, "add ingredient function called");

    }
    public void addRecipeInstructions(View view){

    }

}