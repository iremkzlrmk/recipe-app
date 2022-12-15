package com.meri.recipe_app;

import android.graphics.Bitmap;
import android.location.Location;

public class Recipe {

    private Bitmap image;
    private String name;
    private String ingredients;
    private String makingOf;

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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMakingOf() {
        return makingOf;
    }

    public void setMakingOf(String makingOf) {
        this.makingOf = makingOf;
    }
}
