package com.meri.recipe_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.meri.recipe_app.chef_recipe.ChefRecipe;
import com.meri.recipe_app.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ChefRecipeDAO {

    @Query("select * from chefrecipe")
    List<ChefRecipe> getAllRecipes();

    @Insert
    void insert(ChefRecipe recipe);

    @Insert
    void insertAll(ArrayList<ChefRecipe> recipes);

    @Query("delete from chefrecipe")
    void deleteAll();

}