package com.meri.recipe_app.chef_recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.meri.recipe_app.utils.Converters;
import com.meri.recipe_app.R;

import java.util.ArrayList;

public class ChefRecipeListActivity extends AppCompatActivity {

    ArrayList<ChefRecipe> recipes = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_recipe_list);

        for (int i=0; i < 5; i++){
            ChefRecipe recipe = new ChefRecipe();

            recipe.setRecipeId(i);
            recipe.setName("name" + i);

            Bitmap image = Converters.DrawableToBitmap(getDrawable(R.drawable.ic_launcher_foreground));
            recipe.setImage(image);

            ArrayList<String> list = new ArrayList<>();
            list.add("step" + i);

            recipe.setMakingOf(list);
            recipe.setIngredients(list);

            recipes.add(recipe);
        }

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

}