package com.meri.recipe_app.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.meri.recipe_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeDisplayActivity extends AppCompatActivity {
    ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);

        TextView txtRecipeName = findViewById(R.id.txtNameRecipeDisplay);
        ListView lvIngredients = findViewById(R.id.listRecipeMakingOf);
        ImageView imgRecipeImage = findViewById(R.id.imgRecipeDisplay);

        //-------------------------------------------

        Bundle bundle = getIntent().getExtras();

        txtRecipeName.setText(bundle.getString("recipeName"));
        imgRecipeImage.setImageBitmap(bundle.getParcelable("recipeImage"));

        ingredients = bundle.getStringArrayList("recipeIngredients");
        if (ingredients == null) ingredients = new ArrayList<>();
        ArrayAdapter<String> adapterIngredients = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_array_item, ingredients);
        lvIngredients.setAdapter(adapterIngredients);

        //-------------------------------------------

        ArrayList<String> shoppingList = new ArrayList<>();

        lvIngredients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                String ingredient = ingredients.get(i);

                // TODO: add to shopping list
                boolean inShoppingList = false;

                // check if item in shopping list
                for (String shoppingIngredient : shoppingList) {
                    if (shoppingIngredient.compareTo(ingredient) == 0){
                        inShoppingList = true;
                        break;
                    }
                }

                // remove if in shopping list
                if (inShoppingList) {
                    shoppingList.remove(ingredient);

                    Log.d("cm", "removed from shopping list");
                }
                // add if not in shopping list
                else {
                    shoppingList.add(ingredient);

                    Log.d("cm", "added to shopping list");
                }

                return true;
            }
        });

        Button buttonStart = findViewById(R.id.btnRecipeDisplayMakingof);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: decide if new activity (if so launcher), or fragment (implementing interfaces)
            }
        });

        Button buttonBack = findViewById(R.id.btnDisplayBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}