package com.meri.recipe_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.meri.recipe_app.ShoppingListIngredient;

import java.util.List;

@Dao
public interface ShoppingListIngredientDAO {


    @Query("select ingredient_name from shoppinglistingredient")
    List<String> getAllIngredients();

    @Query("select * from shoppinglistingredient where ingredient_name in (:ingredientNames)")
    List<ShoppingListIngredient> loadAllByIngredientName(String[] ingredientNames);

    @Insert
    void insert(ShoppingListIngredient ingredient);

    @Insert
    void insertAll(ShoppingListIngredient... ingredients);

    @Query("delete from shoppinglistingredient where ingredient_name = (:ingredientNames)")
    void delete(String ingredientNames);

    @Query("delete from shoppinglistingredient")
    void deleteAll();


}
