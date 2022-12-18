package com.meri.recipe_app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class RecipeEditorActivity extends AppCompatActivity implements MakingOfFragment.OnMakingOfEditedListener, IngredientFragment.OnIngredientsEditedListener {

    boolean isIngredientFragment = true;

    String recipeMakingOf;
    String recipeIngredients;

    EditText recipeName;
    ImageView recipeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_editor);

        final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()== Activity.RESULT_OK){
                    Bundle bundle = result.getData().getExtras();
                    Bitmap image = (Bitmap) bundle.get("data");
                    recipeImage.setImageBitmap(image);
                }
            }
        });

        //-------------------------------------------

        recipeName = (EditText) findViewById(R.id.txtRecipeEditorActivityName);

        recipeImage = findViewById(R.id.imgRecipe);
        recipeImage.setImageResource(R.drawable.ic_launcher_foreground);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerRecipeEditorActivity, new IngredientFragment());
        ft.commit();

        //-------------------------------------------

        recipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(intent);
            }
        });

        Button btnToggle = findViewById(R.id.btnRecipeEditorActivityFragmentToggle);
        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if ( isIngredientFragment ){
                    ft.replace(R.id.containerRecipeEditorActivity, new MakingOfFragment());
                    btnToggle.setText("making");
                }
                else{
                    ft.replace(R.id.containerRecipeEditorActivity, new IngredientFragment());
                    btnToggle.setText("ingredients");
                }
                ft.commit();

                isIngredientFragment = !isIngredientFragment;
            }
        });

        // TODO: default values or error handlers for input fields

        Button btnAdd = findViewById(R.id.btnRecipeEditorActivityAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putString("recipeName",recipeName.getText().toString());
                bundle.putString("recipeMakingOf",recipeMakingOf);
                bundle.putParcelable("bitmap",((BitmapDrawable)recipeImage.getDrawable()).getBitmap());
                intent.putExtras(bundle);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        Button btnBack = findViewById(R.id.btnRecipeEditorActivityBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void getEditedMakingOf(String makingOf) {
        recipeMakingOf = makingOf;
    }

    @Override
    public void getEditedIngredients(String ingredients) {
        recipeIngredients = ingredients;
    }
}