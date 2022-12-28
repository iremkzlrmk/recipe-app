package com.meri.recipe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.meri.recipe_app.chef_recipe.ChefRecipeAdapter;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {
    ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        ListView lvShoppingList = findViewById(R.id.listShoppingList);

        //-------------------------------------------

        // TODO: create shopping list
        // TODO: load shopping list
        ArrayAdapter<String> adapterIngredients = new ArrayAdapter<>(getApplicationContext(), R.layout.row_array_item, ingredients);
        lvShoppingList.setAdapter(adapterIngredients);

        //-------------------------------------------

        Button btnBack = findViewById(R.id.btnBackShoppingList);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}