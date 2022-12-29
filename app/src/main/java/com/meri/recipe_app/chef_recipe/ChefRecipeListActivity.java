package com.meri.recipe_app.chef_recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.meri.recipe_app.database.RecipeDatabase;
import com.meri.recipe_app.recipe.Recipe;
import com.meri.recipe_app.recipe.RecipeDisplayActivity;
import com.meri.recipe_app.recipe.RecipeListActivity;
import com.meri.recipe_app.utils.Converters;
import com.meri.recipe_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChefRecipeListActivity extends AppCompatActivity {

    ArrayList<ChefRecipe> recipes = new ArrayList<>();

    RecipeDatabase db;
    ListView listView;

    public boolean containsByRecipeId(ArrayList<ChefRecipe> recipeList, int recipeId){
        for (ChefRecipe recipe : recipeList){
            if (recipe.getRecipeId() == recipeId){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_recipe_list);

        db = Room.databaseBuilder(ChefRecipeListActivity.this,
                RecipeDatabase.class,"recipe-database5").allowMainThreadQueries().build();

        refreshRecipes();

        ChefRecipeAdapter chefRecipeAdapter = new ChefRecipeAdapter(this, recipes);
        listView = findViewById(R.id.listChefRecipe);
        listView.setAdapter(chefRecipeAdapter);

        Button btnBack = findViewById(R.id.btnBackRecipes);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ChefRecipeListActivity.this, ChefRecipeDisplayActivity.class);
                Bundle bundle = new Bundle();

                ChefRecipe recipe = recipes.get(i);
                bundle.putString("recipeName",recipe.getName());
                bundle.putStringArrayList("recipeMakingOf",recipe.getMakingOf());
                bundle.putStringArrayList("recipeIngredients",recipe.getIngredients());
                bundle.putParcelable("recipeImage", recipe.getImage());
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }


    private void refreshRecipes() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                List<ChefRecipe> tempRecipes = db.chefRecipeDAO().getAllRecipes();
                for (ChefRecipe recipe : tempRecipes) {
                    if (!containsByRecipeId(recipes, recipe.getRecipeId())) {
                        recipes.add(recipe);
                    }
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}