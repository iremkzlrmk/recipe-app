package com.meri.recipe_app.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.meri.recipe_app.R;

import java.util.ArrayList;

public class RecipeDisplayActivity extends AppCompatActivity {


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

        ArrayList<String> ingredients = bundle.getStringArrayList("recipeIngredients");
        if (ingredients == null) ingredients = new ArrayList<>();
        ArrayAdapter<String> adapterIngredients = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_array_item, ingredients);
        lvIngredients.setAdapter(adapterIngredients);

        //-------------------------------------------

        lvIngredients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: add to shopping list
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