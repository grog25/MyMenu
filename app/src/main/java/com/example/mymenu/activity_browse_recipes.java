package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

        Button button = (Button) findViewById(R.id.Edit);
        button.setVisibility(View.INVISIBLE);

        Button trash = (Button) findViewById((R.id.trash));
        trash.setVisibility(View.INVISIBLE);

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setVisibility(View.INVISIBLE);

        ImageView background = (ImageView) findViewById(R.id.background);
        background.setVisibility(View.INVISIBLE);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setVisibility(View.INVISIBLE);

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
            if(runnableMethod.getRecipeList().get(i).getDate() == null) {
                ingredient.add("");
                measurement.add(runnableMethod.getRecipeNames().get(i));
            }
        }
        Log.d("getRecipeNames: ", String.valueOf(runnableMethod.getRecipeNames()));
        Log.d("Ingredient: ", String.valueOf(ingredient));
        recyclerView = findViewById(R.id.recipe_list);
        MyAdapter adapter = new MyAdapter(this, ingredient, measurement, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void deleteRecipe() throws InterruptedException {
        for (int i = runnableMethod.getRecipeNames().size() - 1; i > 0; i--) {
            RunnableMethod runnableMethod1 = new RunnableMethod();
            runnableMethod1.setRecipe(runnableMethod.getRecipeList().get(i));
            runnableMethod1.setContext(getApplicationContext());
            runnableMethod1.setMethod("delete");
            Thread thread2 = new Thread(runnableMethod1);
            thread2.start();
            thread2.join();
        }

        Intent intent = new Intent(this, activity_browse_recipes.class);
        startActivity(intent);
    }

    @Override
    public void onRecipeClick(int position) {
        Button button = (Button) findViewById(R.id.Edit);
        button.setVisibility(View.INVISIBLE);

        Button trash = (Button) findViewById((R.id.trash));
        trash.setVisibility(View.INVISIBLE);

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setVisibility(View.INVISIBLE);

        ImageView background = (ImageView) findViewById(R.id.background);
        background.setVisibility(View.INVISIBLE);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setVisibility(View.INVISIBLE);

        trash.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
        background.setVisibility(View.VISIBLE);

        trash.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View view) {
                                         Log.d("Trash", "was clicked");
                                         try {
                                             deleteRecipe();
                                         } catch (InterruptedException e) {
                                             e.printStackTrace();
                                         }
                                     }
                                 });
    }
}