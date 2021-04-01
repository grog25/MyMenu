package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnRecipeListener {

    CalendarView calendarView;
    RecyclerView recyclerView;
    RunnableMethod runnableMethod = new RunnableMethod();
    Thread thread1 = new Thread(runnableMethod);
    String curDate;
    public static final String DATE = "com.example.MenuApp.DATE";
    private static final String TAG = "MainActivity";
    List<String> mealtime = new ArrayList<String>();
    List<String> meal = new ArrayList<String>();
    public Boolean dayMealPlanned = false;
    public int countSameMealPlan = 0;
    public int OriginalMealPlan = 0;
    public int TempMealPlan = 0;

    public void setRecyclerView() throws InterruptedException {
        for(int i = runnableMethod.getRecipeNames().size() - 1; i > 0; i--)
        {
            int currentDate = Integer.valueOf(curDate);
            if(runnableMethod.getRecipeList().get(i).getDate() != null) {
                if(Integer.valueOf(runnableMethod.getRecipeList().get(i).getDate()) == currentDate){
                    if(dayMealPlanned == true){
                        RunnableMethod runnableMethod1 = new RunnableMethod();
                        runnableMethod1.setRecipe(runnableMethod.getRecipeList().get(i));
                        runnableMethod1.setContext(getApplicationContext());
                        runnableMethod1.setMethod("delete");
                        Thread thread2 = new Thread(runnableMethod1);
                        thread2.start();
                        thread2.join();
                        continue;
                    }
                    dayMealPlanned = true;
                    Gson gson = new Gson();
                    meal = gson.fromJson(runnableMethod.getRecipeList().get(i).getMeasurement(), new TypeToken<ArrayList<String>>(){}.getType());
                    mealtime.clear();
                    for(int j = 0; j < meal.size(); j++){
                        mealtime.add("");
                    }
                }
            }
        }

        if(dayMealPlanned == false){
            meal.clear();
            mealtime.clear();
        }
        dayMealPlanned = false;

        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this, mealtime, meal, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        curDate = sdf.format(date.getTime());

        runnableMethod.setMethod("getAll");
        runnableMethod.setContext(getApplicationContext());
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            setRecyclerView();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
                date.set(year, month, dayOfMonth);
                curDate = sdf.format(date.getTime());

                try {
                    setRecyclerView();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "Calendar was clicked " + curDate);

            }
        });

    }


    public void addRecipe(View view){
        /**
         * This method is used to start the addRecipe activity
         */
        Intent intent = new Intent(this, AddRecipe.class);
        startActivity(intent);
    }

    public void browseRecipes(View view){
        /**
         * This method is used to start the browse_recipes activity
         */
        Intent intent = new Intent(this, activity_browse_recipes.class);
        startActivity(intent);
    }

    public void addMeal(View view){
        Intent intent = new Intent(this, AddMeal.class);
        intent.putExtra(DATE, curDate);
        startActivity(intent);
    }

    @Override
    public void onRecipeClick(int position) {

    }
}