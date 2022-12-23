package com.meri.recipe_app.recipe;

import android.graphics.Bitmap;
import android.location.Location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.meri.recipe_app.utils.Converters;

import java.util.ArrayList;

@Entity
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int recipeId;

    private Bitmap image;

    @ColumnInfo(name = "recipe_name")
    private String name;

    @ColumnInfo(name = "recipe_ingredients")
    @TypeConverters(Converters.class)
    private ArrayList<String> ingredients;

    @ColumnInfo(name = "recipe_makingOf")
    @TypeConverters(Converters.class)
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
    public boolean equals(Object obj) {
        return (this == obj);
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
