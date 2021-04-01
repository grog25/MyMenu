package com.example.mymenu;

public class Ingredients {
    private int id;
    private String ingredient;

    public Ingredients() {
    }

    public Ingredients(int id, String ingredient) {
        this.id = id;
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return ingredient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
