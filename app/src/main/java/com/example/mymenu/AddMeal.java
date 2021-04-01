
package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.apache.tools.ant.Main;

import java.util.ArrayList;
import java.util.List;

public class AddMeal extends AppCompatActivity implements MyAdapter.OnRecipeListener {

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    List<String> filler = new ArrayList<String>();
    List<String> filler2 = new ArrayList<String>();
    List<String> recipeName = new ArrayList<String>();
    List<String> meals = new ArrayList<>();
    RunnableMethod runnableMethod = new RunnableMethod();
    Thread thread1 = new Thread(runnableMethod);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        runnableMethod.setMethod("getAll");
        runnableMethod.setContext(getApplicationContext());
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < runnableMethod.getRecipeNames().size(); i++)
        {
            if(runnableMethod.getRecipe().getDate() == null) {
                filler.add("");
                recipeName.add(runnableMethod.getRecipeNames().get(i));
            }
        }
        Log.d("getRecipeNames: ", String.valueOf(runnableMethod.getRecipeNames()));
        Log.d("Ingredient: ", String.valueOf(filler));
        recyclerView = findViewById(R.id.recipe_list);
        MyAdapter adapter = new MyAdapter(this, filler, recipeName, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void saveMeal(View view) throws InterruptedException {
        Intent intent = getIntent();
        String date = intent.getStringExtra(MainActivity.DATE);
        Recipe dayMeal = new Recipe();
        String tempMeal;
        Gson gson = new Gson();
        tempMeal = gson.toJson(meals);
        dayMeal.setMeasurement(tempMeal);
        dayMeal.setDate(date);

        RunnableMethod runnableMethod = new RunnableMethod();
        runnableMethod.setRecipe(dayMeal);
        runnableMethod.setContext(getApplicationContext());
        runnableMethod.setMethod("insert");
        Thread thread1 = new Thread(runnableMethod);
        thread1.start();
        thread1.join();

        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

    @Override
    public void onRecipeClick(int position) {
        filler.get(position);

        Log.d("Recipe Name: ", recipeName.get(position));

        meals.add(recipeName.get(position));
        filler2.add("");

        recyclerView2 = findViewById(R.id.meal_plan);
        MyAdapter adapter = new MyAdapter(this, filler2, meals, this);
        recyclerView2.setAdapter(adapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
    }
}
