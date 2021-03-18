package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DayView extends AppCompatActivity {

    private boolean clickBreakfast;
    private boolean clickLunch;
    private boolean clickDinner;
    private boolean clickDesert;
    private boolean clickSideDish;
    private boolean clickOther;

    private static final String[] date = {"item 1", "item 2", "item 3"};
    private static final String[] meals = {"Breakfast", "Lunch", "Dinner"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);
        Spinner spinner1 = findViewById(R.id.dateDropDown);
        ArrayAdapter<String>adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, date);

        Spinner spinner2 = findViewById(R.id.mealSelector);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, meals);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

    }

    /*@Override*/
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    /*@Override*/
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}