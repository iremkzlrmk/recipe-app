package com.meri.recipe_app.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meri.recipe_app.R;
import com.meri.recipe_app.chef_recipe.ChefRecipe;
import com.meri.recipe_app.database.RecipeDatabase;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {

    RecipeDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button btnAddChefRecipes = findViewById(R.id.btnAddChefRecipesAdmin);
        Button btnDeleteChefRecipes = findViewById(R.id.btnDeleteChefRecipesAdmin);
        Button btnBack = findViewById(R.id.btnBackAdmin);

        //-------------------------------------------

        db = Room.databaseBuilder(Admin.this, RecipeDatabase.class,
                "recipe-database5").allowMainThreadQueries().build();

        //-------------------------------------------

        btnAddChefRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ChefRecipe recipe = new ChefRecipe();
                recipe.setName("doner kebab");
                recipe.setImage(Converters.DrawableToBitmap(getDrawable(R.drawable.doner)));

                ArrayList<String> ingredients = new ArrayList<>();
                ingredients.add("doner");
                ingredients.add("tomato");
                ingredients.add("bread");
                ingredients.add("potato");
                ingredients.add("onion");
                recipe.setIngredients(ingredients);

                ArrayList<String> makingOf = new ArrayList<>();
                makingOf.add("prepare bread");
                makingOf.add("slice tomato and potatoes");
                makingOf.add("cook them");
                makingOf.add("smash the onion");
                makingOf.add("serve ingredients over bread");
                recipe.setMakingOf(makingOf);

                db.chefRecipeDAO().insert(recipe);

                //-------------------------------------------

                recipe = new ChefRecipe();
                recipe.setName("pasta alla vodka");
                recipe.setImage(Converters.DrawableToBitmap(getDrawable(R.drawable.pasta)));

                ingredients = new ArrayList<>();
                ingredients.add("pasta");
                ingredients.add("tomato sauce");
                ingredients.add("mozzarella");
                ingredients.add("pepper");
                ingredients.add("onion");
                ingredients.add("garlic");
                ingredients.add("cream");
                recipe.setIngredients(ingredients);

                makingOf = new ArrayList<>();
                makingOf.add("cook pasta");
                makingOf.add("cook garlic and onion");
                makingOf.add("add tomato sauce");
                makingOf.add("add cream");
                makingOf.add("add pepper");
                makingOf.add("add pasta");
                recipe.setMakingOf(makingOf);

                db.chefRecipeDAO().insert(recipe);

                //-------------------------------------------

                recipe = new ChefRecipe();
                recipe.setName("tantuni with yoghurt");
                recipe.setImage(Converters.DrawableToBitmap(getDrawable(R.drawable.tantuni)));

                ingredients = new ArrayList<>();
                ingredients.add("meat");
                ingredients.add("tomato sauce");
                ingredients.add("mozzarella");
                ingredients.add("pepper");
                ingredients.add("onion");
                ingredients.add("garlic");
                ingredients.add("cream");
                ingredients.add("yoghurt");
                recipe.setIngredients(ingredients);

                makingOf = new ArrayList<>();
                makingOf.add("cook the meat");
                makingOf.add("cook garlic and onion");
                makingOf.add("add tomato sauce");
                makingOf.add("prepare bread");
                makingOf.add("add ingredients into bread");
                makingOf.add("slice to serve");
                makingOf.add("add yoghurt and sauce onto the tantuni");
                recipe.setMakingOf(makingOf);

                db.chefRecipeDAO().insert(recipe);

                //-------------------------------------------

                recipe = new ChefRecipe();
                recipe.setName("iskender");
                recipe.setImage(Converters.DrawableToBitmap(getDrawable(R.drawable.iskender)));

                ingredients = new ArrayList<>();
                ingredients.add("doner");
                ingredients.add("tomato sauce");
                ingredients.add("yoghurt");
                ingredients.add("pepper");
                ingredients.add("butter");
                ingredients.add("tomato");
                ingredients.add("bread");
                recipe.setIngredients(ingredients);

                makingOf = new ArrayList<>();
                makingOf.add("cook the meat");
                makingOf.add("add tomato sauce, pepper and butter to the pan");
                makingOf.add("prepare serving plate");
                makingOf.add("slice and place breads onto the plate");
                makingOf.add("add ingredients over breads");
                makingOf.add("add sauce and serve");
                recipe.setMakingOf(makingOf);

                db.chefRecipeDAO().insert(recipe);

                //-------------------------------------------

                recipe = new ChefRecipe();
                recipe.setName("croissant");
                recipe.setImage(Converters.DrawableToBitmap(getDrawable(R.drawable.croissant)));

                ingredients = new ArrayList<>();
                ingredients.add("dough");
                ingredients.add("flour");
                ingredients.add("butter");
                recipe.setIngredients(ingredients);

                makingOf = new ArrayList<>();
                makingOf.add("prepare the dough with flour");
                makingOf.add("butter and give shape to the dough");
                makingOf.add("bake the dough");
                recipe.setMakingOf(makingOf);

                db.chefRecipeDAO().insert(recipe);

                //-------------------------------------------

                recipe = new ChefRecipe();
                recipe.setName("mini burgers");
                recipe.setImage(Converters.DrawableToBitmap(getDrawable(R.drawable.miniburger)));

                ingredients = new ArrayList<>();
                ingredients.add("bread");
                ingredients.add("meat");
                ingredients.add("tomato");
                ingredients.add("lettuce");
                ingredients.add("cheddar");
                recipe.setIngredients(ingredients);

                makingOf = new ArrayList<>();
                makingOf.add("cook the meat");
                makingOf.add("prepare ingredients");
                makingOf.add("prepare special sauce with adding ingredients");
                makingOf.add("put ingredients inside breads");
                recipe.setMakingOf(makingOf);

                db.chefRecipeDAO().insert(recipe);
            }
        });

        btnDeleteChefRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.chefRecipeDAO().deleteAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}