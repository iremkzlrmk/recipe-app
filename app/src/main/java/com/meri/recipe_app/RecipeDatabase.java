package com.meri.recipe_app;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Recipe.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class RecipeDatabase extends RoomDatabase{
    public abstract RecipeDAO recipeDAO();
}
