package com.example.mymenu;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity
    //created recipe class for the different recipe objects
    public class Recipe implements Serializable {
        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "recipeName")
        private String recipeName;

        @ColumnInfo(name = "measurement")
        private String measurement;

        @ColumnInfo(name = "ingredient")
        private String ingredient;

        @ColumnInfo(name = "instructions")
        private String instructions;

        @ColumnInfo(name = "category")
        private String category;

        @ColumnInfo(name = "date")
        private String date;

        public String getDate() { return date; }

        public int getId() {
            return id;
        }

        public String getIngredient() {
            return ingredient;
        }

        public String getMeasurement() {
            return measurement;
        }

        public String getRecipeName() {
            return recipeName;
        }

        public String getInstructions() {
            return instructions;
        }

        public String getCategory() {
            return category;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setDate(String date) { this.date = date; }

        public void setIngredient(String ingredient) {
            this.ingredient = ingredient;
        }

        public void setMeasurement(String measurement) {
            this.measurement = measurement;
        }

        public void setRecipeName(String recipeName) {
            this.recipeName = recipeName;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public void setCategory(String category) {
            this.category = category;
        }
}


