package com.meri.recipe_app.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.meri.recipe_app.MainActivity;
import com.meri.recipe_app.R;

import java.util.ArrayList;

public class RecipeDisplayActivity extends AppCompatActivity {

    boolean isIngredientFragment = true;

    ArrayList<String> ingredients;
    ArrayList<String> makingOfs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);

        TextView txtRecipeName = findViewById(R.id.txtNameRecipeDisplay);

        ImageView imgRecipeImage = findViewById(R.id.imgRecipeDisplay);

        //-------------------------------------------

        Bundle bundle = getIntent().getExtras();

        txtRecipeName.setText(bundle.getString("recipeName"));
        imgRecipeImage.setImageBitmap(bundle.getParcelable("recipeImage"));

        ingredients = bundle.getStringArrayList("recipeIngredients");
        if (ingredients == null) ingredients = new ArrayList<>();
        makingOfs = bundle.getStringArrayList("recipeMakingOf");

        ArrayAdapter<String> adapterIngredients = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_array_item, ingredients);

        //-------------------------------------------

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerRecipeDisplayActivity, IngredientDisplayFragment.newInstance(ingredients));
        ft.commit();

        Button buttonStart = findViewById(R.id.btnRecipeDisplayMakingof);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: decide if new activity (if so launcher), or fragment (implementing interfaces)

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if ( isIngredientFragment ){
                    ft.replace(R.id.containerRecipeDisplayActivity, MakingOfDisplayFragment.newInstance(makingOfs));
                    buttonStart.setText("done");

                }
                else{
                    Intent intent = new Intent(RecipeDisplayActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                ft.commit();

                isIngredientFragment = !isIngredientFragment;

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