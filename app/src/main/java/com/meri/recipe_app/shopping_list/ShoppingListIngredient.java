package com.meri.recipe_app.shopping_list;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ShoppingListIngredient {

    @PrimaryKey(autoGenerate = true)
    private int ingredientId;

    @ColumnInfo(name = "ingredient_name")
    private String ingredientName;

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
