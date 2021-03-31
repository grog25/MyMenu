package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class activity_browse_recipes extends AppCompatActivity implements MyAdapter.OnRecipeListener {
    RecyclerView recyclerView;
    List<String> ingredient = new ArrayList<String>();
    List<String> measurement = new ArrayList<String>();
    RunnableMethod runnableMethod = new RunnableMethod();
    Thread thread1 = new Thread(runnableMethod);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_recipes);

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
            ingredient.add("");
            measurement.add(runnableMethod.getRecipeNames().get(i));
        }
        Log.d("getRecipeNames: ", String.valueOf(runnableMethod.getRecipeNames()));
        Log.d("Ingredient: ", String.valueOf(ingredient));
        recyclerView = findViewById(R.id.browse_recipes);
        MyAdapter adapter = new MyAdapter(this, ingredient, measurement, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRecipeClick(int position) {
        ingredient.get(position);
        Intent intent = new Intent(this, AddRecipe.class);
        startActivity(intent);
    }
}