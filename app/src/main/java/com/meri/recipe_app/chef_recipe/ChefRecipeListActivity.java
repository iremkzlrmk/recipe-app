package com.meri.recipe_app.chef_recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.meri.recipe_app.database.RecipeDatabase;
import com.meri.recipe_app.recipe.Recipe;
import com.meri.recipe_app.recipe.RecipeListActivity;
import com.meri.recipe_app.utils.Converters;
import com.meri.recipe_app.R;

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


    public void addRecipe(){
        for (int i=0; i < 5; i++){
            ChefRecipe recipe = new ChefRecipe();

//            recipe.setRecipeId(i);
            recipe.setName("name" + i);

            Bitmap image = Converters.DrawableToBitmap(getDrawable(R.drawable.ic_launcher_foreground));
            recipe.setImage(image);

            ArrayList<String> list = new ArrayList<>();
            list.add("step" + i);

            recipe.setMakingOf(list);
            recipe.setIngredients(list);

            recipes.add(recipe);
        }

        db.chefRecipeDAO().insertAll(recipes);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_recipe_list);

        db = Room.databaseBuilder(ChefRecipeListActivity.this,
                RecipeDatabase.class,"recipe-database").allowMainThreadQueries().fallbackToDestructiveMigration().build();

//        addRecipe();
//        db.chefRecipeDAO().deleteAll();

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