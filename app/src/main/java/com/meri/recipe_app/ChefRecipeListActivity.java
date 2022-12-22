package com.meri.recipe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ChefRecipeListActivity extends AppCompatActivity {

    ArrayList<ChefRecipe> recipes = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_recipe_list);

        recipes.add(new ChefRecipe());
        recipes.add(new ChefRecipe());
        recipes.add(new ChefRecipe());
        recipes.add(new ChefRecipe());

        ChefRecipeAdapter chefRecipeAdapter = new ChefRecipeAdapter(this, recipes);
        listView = findViewById(R.id.lvRecipe);
        listView.setAdapter(chefRecipeAdapter);

        Button btnBack = findViewById(R.id.btnBackRecipes);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}