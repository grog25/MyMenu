package com.example.mymenu;
import org.junit.Test;
import static com.example.mymenu.AddRecipe.*;
import static org.junit.Assert.assertThat;


public class testAddRecipe {
    @Test
    public void testAddRecipe() {
        AddRecipe recipe = new AddRecipe();
        assertThat(recipe.addIngredient() );
    }
}