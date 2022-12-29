package com.meri.recipe_app.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.meri.recipe_app.ShoppingListIngredient;
import com.meri.recipe_app.chef_recipe.ChefRecipe;
import com.meri.recipe_app.utils.Converters;
import com.meri.recipe_app.recipe.Recipe;

@Database(entities = {Recipe.class, ChefRecipe.class, ShoppingListIngredient.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class RecipeDatabase extends RoomDatabase{
    public abstract RecipeDAO recipeDAO();
    public abstract ChefRecipeDAO chefRecipeDAO();
    public abstract ShoppingListIngredientDAO shoppingListIngredientDAO();
}
