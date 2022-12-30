package com.meri.recipe_app.shopping_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.meri.recipe_app.R;
import com.meri.recipe_app.database.RecipeDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    RecipeDatabase db;
    ArrayList<String> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        db = Room.databaseBuilder(ShoppingListActivity.this, RecipeDatabase.class, "recipe-database5").build();

        ListView lvShoppingList = findViewById(R.id.listShoppingList);

        //-------------------------------------------

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
//                db.shoppingListIngredientDAO().deleteAll();
                List<String> tempList = db.shoppingListIngredientDAO().getAllIngredients();
                ingredients.addAll(tempList);
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapterIngredients = new ArrayAdapter<>(getApplicationContext(), R.layout.row_array_item, ingredients);
        lvShoppingList.setAdapter(adapterIngredients);

        //-------------------------------------------

        ArrayList<String> boughtItems = new ArrayList<>();

        lvShoppingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String ingredient = ingredients.get(i);

                boolean inBoughtItems = false;

                for (String item : boughtItems) {
                    if (item.compareTo(ingredient) == 0){
                        inBoughtItems = true;
                        break;
                    }
                }

                // remove if in steps list
                if (inBoughtItems) {
                    ShoppingListIngredient tempIngredient = new ShoppingListIngredient();
                    tempIngredient.setIngredientName(ingredient);
                    boughtItems.remove(ingredient);
                    view.setBackgroundResource(R.drawable.rounded_shape);
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            db.shoppingListIngredientDAO().insert(tempIngredient);
                        }
                    });
                    t.start();
                    Log.d("cm", "removed from shopping list");
                }
                // add if not in steps list
                else {
                    boughtItems.add(ingredient);
                    view.setBackgroundResource(R.drawable.rounded_shape_done);
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            db.shoppingListIngredientDAO().delete(ingredient);
                        }
                    });
                    t.start();
                    Log.d("cm", "added to shopping list");
                }

            }
        });

        Button btnBack = findViewById(R.id.btnBackShoppingList);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}