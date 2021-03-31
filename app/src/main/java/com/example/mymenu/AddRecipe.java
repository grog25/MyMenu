package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddRecipe extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btn_save, btn_addIngredient;
    EditText et_recipeName, et_mNumber, et_ingredient, et_instructions;
    ListView lv_ingredients;

    private static final String[] mealType = {"Breakfast", "Lunch", "Dinner", "Desert", "Side Dish", "Other"};
    private static final String[] mUnits = {"pinch", "dash", "cups", "tsp", "tbsp", "ounces", "lbs", "ml", "liters"};
    private static final String[] fractions = {"1/8", "1/4", "1/2", "2/3", "3/4",};

    ArrayAdapter recipeArrayAdapter;
    DataBaseHelper dataBaseHelper;

    private static final String TAG = "AddRecipe";
    List<String> ingredient = new ArrayList<String>();
    List<String> measurement = new ArrayList<String>();
    List<String> recipes = new ArrayList<String>();
    Recipe recipe = new Recipe();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        et_ingredient = findViewById(R.id.et_ingredient);
        et_mNumber = findViewById(R.id.et_mNumber);
        et_recipeName = findViewById(R.id.et_recipeName);
        et_instructions = findViewById(R.id.et_instructions);
        btn_addIngredient = findViewById(R.id.btn_addIngredient);
        btn_save = findViewById(R.id.btn_save);
        lv_ingredients = findViewById(R.id.lv_ingredients);

        Spinner sp_fraction = findViewById(R.id.sp_fraction);
        ArrayAdapter<String>adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, fractions);

        Spinner sp_mUnit = findViewById(R.id.mealSelector);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mUnits);

        Spinner sp_mealType = findViewById(R.id.sp_mealType);
        ArrayAdapter<String>adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mealType);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_fraction.setAdapter(adapter1);
        sp_fraction.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_mUnit.setAdapter(adapter2);
        sp_mUnit.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_mealType.setAdapter(adapter3);
        sp_mealType.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        //ingredient.add("Ingredients");
        //measurement.add("Measurements");

        dataBaseHelper = new DataBaseHelper(AddRecipe.this);

        ShowIngredientsOnListView(dataBaseHelper);

        // button listeners for the add and view all buttons
        btn_save.setOnClickListener(v -> {

            RecipeModel recipeModel;

            try {
                recipeModel = new RecipeModel(1, et_recipeName.getText().toString(),
                        et_measurement.getText().toString(), et_ingredient.getText().toString(), cb_Dinner.isChecked());
                Toast.makeText(AddRecipe.this, recipeModel.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(AddRecipe.this, "Error creating recipe", Toast.LENGTH_SHORT).show();
                recipeModel = new RecipeModel(-1, "error", "error", "error",false);
            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(AddRecipe.this);

            boolean success = dataBaseHelper.addRecipe(RecipeModel);

            Toast.makeText(AddRecipe.this, "Success " + success, Toast.LENGTH_SHORT).show();

        });

        btn_addIngredient.setOnClickListener(v -> {

            dataBaseHelper = new DataBaseHelper(AddRecipe.this);

            ShowCustomersOnListView(dataBaseHelper);

            //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

        });

        lv_ingredients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingredients clickedIngredient = (Ingredients) parent.getItemAtPosition(position);
                dataBaseHelper.editOne(clickedIngredient);
                ShowIngredientsOnListView(dataBaseHelper);
                Toast.makeText(AddRecipe.this, "Deleted " + clickedIngredient.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void ShowIngredientsOnListView(DataBaseHelper dataBaseHelper2) {
        recipeArrayAdapter = new ArrayAdapter<Ingredients>
                (AddRecipe.this, android.R.layout.simple_list_item_1, dataBaseHelper2.getEveryone());
        lv_ingredients.setAdapter(recipeArrayAdapter);
    }


    public void addIngredient(View view){
        Log.d(TAG, "add ingredient function called");

        EditText enterIngredient = (EditText) findViewById(R.id.et_ingredient);
        ingredient.add(enterIngredient.getText().toString());
        enterIngredient.setText("");

        EditText enterMeasurement = (EditText) findViewById(R.id.et_mNumber);
        measurement.add(enterMeasurement.getText().toString());
        enterMeasurement.setText("");

        MyAdapter adapter = new MyAdapter(this, ingredient, measurement);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void saveRecipe() {
        EditText recipeName = (EditText) findViewById(R.id.et_recipeName);
        recipe.setRecipeName(recipeName.getText().toString());
    }

    public void nextActivity(View view){
        Intent intent = new Intent(this, activity_recipe_instructions.class);
        startActivity(intent);
    }


}