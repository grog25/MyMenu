package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    List<String> mealtime = new ArrayList<String>();
    List<String> meal = new ArrayList<String>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mealtime.add("Breakfast");
        mealtime.add("Breakfast");
        mealtime.add("Lunch");
        mealtime.add("Lunch");
        mealtime.add("Dinner");
        mealtime.add("Dinner");
        mealtime.add("Dinner");

        meal.add("Pancakes");
        meal.add("Bacon");
        meal.add("Turkey Sandwich");
        meal.add("Chips");
        meal.add("Roasted Chicken");
        meal.add("Mashed Potatoes");
        meal.add("Mixed Veggies");




        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this, mealtime, meal);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {


            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Log.d(TAG, "Calendar was clicked");

            }
        });

    }

    public void addRecipe(View view){
        Intent intent = new Intent(this, AddRecipe.class);
        startActivity(intent);
    }
}