package com.example.mymenu;

public class RecipeModel {

    private int id;
    private String mealName;
    private int mNumber;
    private String fraction;
    private String mUnit;
    private String ingredients;
    private String mealType;
    private String instructions;

    public RecipeModel(int id, String mealName, int mNumber, String fraction, String mUnit, String ingredients, String mealType, String instructions) {
        this.id = id;
        this.mealName = mealName;
        this.mNumber = mNumber;
        this.fraction = fraction;
        this.mUnit = mUnit;
        this.ingredients = ingredients;
        this.mealType = mealType;
        this.instructions = instructions;
    }


}
