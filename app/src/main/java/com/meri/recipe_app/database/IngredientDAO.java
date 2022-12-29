package com.meri.recipe_app.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.meri.recipe_app.Ingredient;

import java.util.List;

@Dao
public interface IngredientDAO {

    @Query("select * from ")
    List<Ingredient> getAllIngredients();

}
