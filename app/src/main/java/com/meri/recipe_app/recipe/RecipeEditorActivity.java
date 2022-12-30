package com.meri.recipe_app.recipe;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.meri.recipe_app.R;

import java.util.ArrayList;

public class RecipeEditorActivity extends AppCompatActivity implements MakingOfFragment.OnMakingOfEditedListener, IngredientFragment.OnIngredientsEditedListener {

    boolean isIngredientFragment = true;

    public ArrayList<String> editedRecipeMakingOf = new ArrayList<>();
    public ArrayList<String> editedRecipeIngredients = new ArrayList<>();

    EditText txtRecipeName;
    ImageView imgRecipeImage;

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
                    imgRecipeImage.setImageBitmap(image);
                }
            }
        });

        //-------------------------------------------

        txtRecipeName = (EditText) findViewById(R.id.txtRecipeEditorActivityName);
        imgRecipeImage = findViewById(R.id.imgRecipe);
        imgRecipeImage.setImageResource(R.drawable.korean);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerRecipeEditorActivity, IngredientFragment.newInstance(editedRecipeIngredients));
        ft.commit();

        //-------------------------------------------

        imgRecipeImage.setOnClickListener(new View.OnClickListener() {
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
                    ft.replace(R.id.containerRecipeEditorActivity, MakingOfFragment.newInstance(editedRecipeMakingOf));
                    btnToggle.setText("making");
                }
                else{
                    ft.replace(R.id.containerRecipeEditorActivity, IngredientFragment.newInstance(editedRecipeIngredients));
                    btnToggle.setText("ingredients");
                }
                ft.commit();

                isIngredientFragment = !isIngredientFragment;
            }
        });

        Button btnAdd = findViewById(R.id.btnRecipeEditorActivityAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String recipeName = txtRecipeName.getText().toString();
                Bitmap recipeImage = ((BitmapDrawable)imgRecipeImage.getDrawable()).getBitmap();

                // error handlers
                if (recipeName.equals("")) {
                    Toast.makeText(RecipeEditorActivity.this, "Please provide recipe name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("recipeName",recipeName);
                bundle.putStringArrayList("recipeMakingOf",editedRecipeMakingOf);
                bundle.putStringArrayList("recipeIngredients", editedRecipeIngredients);
                bundle.putParcelable("bitmap",recipeImage);
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
    public void addIngredient(String ingredients) {
        editedRecipeIngredients.add(ingredients);
    }

    @Override
    public void addMakingOf(String makingOf) {
        editedRecipeMakingOf.add(makingOf);
    }
}