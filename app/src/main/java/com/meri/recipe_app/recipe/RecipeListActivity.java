package com.meri.recipe_app.recipe;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.meri.recipe_app.R;
import com.meri.recipe_app.shopping_list.ShoppingListIngredient;
import com.meri.recipe_app.database.RecipeDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    static ArrayList<Recipe> recipes = new ArrayList<>();

    RecipeDatabase db;
    ListView listView;

    public boolean containsByRecipeId(ArrayList<Recipe> recipeList, int recipeId){
        for (Recipe recipe : recipeList){
            if (recipe.getRecipeId() == recipeId){
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        db = Room.databaseBuilder(RecipeListActivity.this,
                RecipeDatabase.class,"recipe-database5").allowMainThreadQueries().build();

        refreshRecipes();

        RecipeAdapter recipeAdapter = new RecipeAdapter(this, recipes);
        listView = findViewById(R.id.lvRecipe);
        listView.setAdapter(recipeAdapter);

        final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Recipe recipe = new Recipe();

                    recipe.setName(data.getStringExtra("recipeName"));
                    recipe.setMakingOf(data.getStringArrayListExtra("recipeMakingOf"));
                    recipe.setIngredients(data.getStringArrayListExtra("recipeIngredients"));
                    recipe.setImage((Bitmap) data.getParcelableExtra("bitmap"));

                    RecipeInserter ri = new RecipeInserter();
                    Recipe[] rs = new Recipe[1];
                    rs[0] = recipe;
                    ri.execute(rs);

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
                bundle.putString("recipeName",recipe.getName());
                bundle.putStringArrayList("recipeMakingOf",recipe.getMakingOf());
                bundle.putStringArrayList("recipeIngredients",recipe.getIngredients());
                bundle.putParcelable("recipeImage", recipe.getImage());
                intent.putExtras(bundle);

                launcher.launch(intent);
            }
        });
    }


    private void refreshRecipes() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Recipe> tempRecipes = db.recipeDAO().getAllRecipes();
                for (Recipe recipe : tempRecipes){
                    if (!containsByRecipeId(recipes, recipe.getRecipeId())){
                        recipes.add(recipe);
                    }
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class RecipeInserter extends AsyncTask<Recipe,Void,List<Recipe>>{

        @Override
        protected List<Recipe> doInBackground(Recipe... recipes) {
            db.recipeDAO().insert(recipes[0]);
            db.shoppingListIngredientDAO().insert(new ShoppingListIngredient());
            return db.recipeDAO().getAllRecipes();
        }

        @Override
        protected void onPostExecute(List<Recipe> recipez) {
            super.onPostExecute(recipez);
            recipes.clear();
            recipes.addAll(recipez);
            ((RecipeAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
    }
}
