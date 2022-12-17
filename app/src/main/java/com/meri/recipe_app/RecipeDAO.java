package com.meri.recipe_app;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Query("select * from recipe")
    List<Recipe> getAllRecipes();

    @Query("select * from recipe where recipe_name in (:recipeNames)")
    List<Recipe> loadAllByRecipeName(String[] recipeNames);

    @Insert
    void insert(Recipe recipe);

    @Insert
    void insertAll(Recipe... recipes);

    @Delete
    void delete(Recipe recipe);

}
