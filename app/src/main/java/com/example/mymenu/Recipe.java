package com.example.mymenu;

import java.util.List;

//created recipe class for the different recipe objects
public class Recipe {
    private String recipeName;
    private List<String> measurement;
    private List<String> ingredient;
    private String instructions;

    public List<String> getIngredient() {
        return ingredient;
    }

    public List<String> getMeasurement() {
        return measurement;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getInstructions() {
        return instructions;
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

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
