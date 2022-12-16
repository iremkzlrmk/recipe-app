package com.meri.recipe_app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class RecipeEditorActivity extends AppCompatActivity {

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


        EditText recipeName = (EditText) findViewById(R.id.txtRecipeName);
        EditText recipeMaking = (EditText) findViewById(R.id.txtRecipeMakingOf);
        recipeImage = findViewById(R.id.imgRecipe);
        recipeImage.setImageResource(R.drawable.ic_launcher_foreground);

        recipeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                ActivityResultLauncher<Intent> launcher = null;
                launcher.launch(intent);
            }
        });

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("recipeName",recipeName.getText());
                bundle.putCharSequence("recipeMakingOf",recipeMaking.getText());
                bundle.putParcelable("bitmap",((BitmapDrawable)recipeImage.getDrawable()).getBitmap());
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}