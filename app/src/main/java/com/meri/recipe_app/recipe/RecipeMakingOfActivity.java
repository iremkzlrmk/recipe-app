package com.meri.recipe_app.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.meri.recipe_app.R;

import java.util.ArrayList;

public class RecipeMakingOfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_making_of);

        TextView txtRecipeName = findViewById(R.id.txtNameRecipeMakingOf);
        ListView lvMakingOf = findViewById(R.id.listRecipeMakingOf);
        ImageView imgRecipeImage = findViewById(R.id.imgRecipeMakingOf);

        //-------------------------------------------

        Bundle bundle = getIntent().getExtras();

        txtRecipeName.setText(bundle.getString("recipeName"));
        imgRecipeImage.setImageBitmap(bundle.getParcelable("recipeImage"));

        ArrayList<String> ingredients = bundle.getStringArrayList("recipeIngredients");
        if (ingredients == null) ingredients = new ArrayList<>();
        ArrayAdapter<String> adapterIngredients = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_array_item, ingredients);
        lvMakingOf.setAdapter(adapterIngredients);

        // TODO: decide if new activity or just fragment
    }
}