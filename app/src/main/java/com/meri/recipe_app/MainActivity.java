package com.meri.recipe_app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meri.recipe_app.chef_recipe.ChefRecipeListActivity;
import com.meri.recipe_app.recipe.RecipeListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}