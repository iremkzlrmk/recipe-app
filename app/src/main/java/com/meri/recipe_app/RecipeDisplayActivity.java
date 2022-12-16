package com.meri.recipe_app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeDisplayActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_display);

        TextView displayRecipeName = findViewById(R.id.txtDisplayRecipeName);
        TextView displayRecipeMakingOf = findViewById(R.id.txtDisplayRecipeMakingOf);
        ImageView displayRecipeImage = findViewById(R.id.imgDisplayRecipe);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            displayRecipeName.setText(bundle.getCharSequence("recipeName", null));
            displayRecipeMakingOf.setText(bundle.getCharSequence("recipeMakingOf", null));
            displayRecipeImage.setImageBitmap(bundle.getParcelable("recipeImage"));
        }


        Button buttonBack = findViewById(R.id.btnDisplayBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(RecipeDisplayActivity.this, MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });



    }
}