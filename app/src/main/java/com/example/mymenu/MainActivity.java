package com.example.mymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hello Tanner and Jeffery!");
        System.out.println("Nice weather we're all having!");
        System.out.println("Makes me want to dance a Jig!");
    }
}