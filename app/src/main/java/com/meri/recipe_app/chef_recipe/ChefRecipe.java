package com.meri.recipe_app.chef_recipe;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class ChefRecipe {

    private int recipeId;

    private Bitmap image;

    private String name;

    private ArrayList<String> ingredients;

    private ArrayList<String> makingOf;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getMakingOf() {
        return makingOf;
    }

    public void setMakingOf(ArrayList<String> makingOf) {
        this.makingOf = makingOf;
    }

    @Override
    public String toString() {
        return  "Recipe{" +
                "recipeId=" + recipeId +
                ", name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", makingOf=" + makingOf +
                '}';
    }


}
