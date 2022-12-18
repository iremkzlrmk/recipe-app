package com.meri.recipe_app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {

    static ArrayList<Recipe> recipes = new ArrayList<>();

    RecipeDatabase db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        db = Room.databaseBuilder(RecipeListActivity.this,
                RecipeDatabase.class,"recipe-database").build();

        listView = findViewById(R.id.lvRecipe);
        RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipes);
        listView.setAdapter(recipeAdapter);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                recipes.addAll(db.recipeDAO().getAllRecipes());
            }
        });

        final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Recipe recipe = new Recipe();

                    recipe.setName(data.getStringExtra("recipeName"));
                    recipe.setMakingOf(data.getStringExtra("recipeMakingOf"));
                    recipe.setImage((Bitmap) data.getParcelableExtra("bitmap"));

                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            db.recipeDAO().insert(recipe);
                        }
                    });
                    t.start();

                    recipes.add(recipe);
                    ((RecipeAdapter) listView.getAdapter()).notifyDataSetChanged();
                }
            }
        });

        Button btnAddRecipe = findViewById(R.id.btnAddRecipe);
        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecipeListActivity.this, RecipeEditorActivity.class);
                launcher.launch(intent);
            }
        });

        Button btnBack = findViewById(R.id.btnBackRecipes);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RecipeListActivity.this, RecipeDisplayActivity.class);
                Bundle bundle = new Bundle();
                Recipe recipe = recipes.get(i);
                bundle.putCharSequence("recipeName",recipe.getName());
                bundle.putCharSequence("recipeMakingOf",recipe.getMakingOf());
                bundle.putCharSequence("recipeIngredients",recipe.getIngredients());
                bundle.putParcelable("recipeImage", recipe.getImage());
                intent.putExtras(bundle);
//                startActivity(intent);
                launcher.launch(intent);
            }
        });

    }
}
