package com.example.mymenu;

import java.util.List;

public class Recipe {
    private String recipeName;
    private List<String> measurement;
    private List<String> ingredient;

    public List<String> getIngredient() {
        return ingredient;
    }

    public List<String> getMeasurement() {
        return measurement;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setIngredient(List<String> ingredient) {
        this.ingredient = ingredient;
    }

    public void setMeasurement(List<String> measurement) {
        this.measurement = measurement;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
