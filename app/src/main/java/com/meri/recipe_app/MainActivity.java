package com.meri.recipe_app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.meri.recipe_app.chef_recipe.ChefRecipe;
import com.meri.recipe_app.chef_recipe.ChefRecipeDisplayActivity;
import com.meri.recipe_app.chef_recipe.ChefRecipeListActivity;
import com.meri.recipe_app.database.RecipeDatabase;
import com.meri.recipe_app.recipe.Recipe;
import com.meri.recipe_app.recipe.RecipeDisplayActivity;
import com.meri.recipe_app.recipe.RecipeListActivity;
import com.meri.recipe_app.shopping_list.ShoppingListActivity;
import com.meri.recipe_app.utils.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecipeDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(MainActivity.this,
                RecipeDatabase.class,"recipe-database5").allowMainThreadQueries().build();

        final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {}
        });

        Button btnGoMyRecipes = findViewById(R.id.btnGoMyRecipes);
        btnGoMyRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                launcher.launch(intent);
            }
        });

        Button btnGoChefRecipes = findViewById(R.id.btnGoRecipes);
        btnGoChefRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChefRecipeListActivity.class);
                launcher.launch(intent);
            }
        });

        Button btnSurpriseMe = findViewById(R.id.btnSurpriseMain);
        btnSurpriseMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChefRecipeDisplayActivity.class);
                Bundle bundle = new Bundle();
                Random random = new Random();

                List<ChefRecipe> recipes = db.chefRecipeDAO().getAllRecipes();
                // error handler
                if (recipes.size() <= 0) {
                    Toast.makeText(MainActivity.this, "No chef recipe found", Toast.LENGTH_SHORT).show();
                    return;
                }

                ChefRecipe recipe = recipes.get(random.nextInt(recipes.size()));

                bundle.putString("recipeName",recipe.getName());
                bundle.putStringArrayList("recipeMakingOf",recipe.getMakingOf());
                bundle.putStringArrayList("recipeIngredients",recipe.getIngredients());
                bundle.putParcelable("recipeImage", recipe.getImage());
                intent.putExtras(bundle);

                launcher.launch(intent);
            }
        });

        Button btnShoppingList = findViewById(R.id.btnShoppingList);
        btnShoppingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShoppingListActivity.class);
                launcher.launch(intent);
            }
        });
        btnShoppingList.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MainActivity.this, Admin.class);
                launcher.launch(intent);
                return true;
            }
        });
    }
}