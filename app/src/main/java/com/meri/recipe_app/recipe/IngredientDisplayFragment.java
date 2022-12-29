package com.meri.recipe_app.recipe;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.meri.recipe_app.R;
import com.meri.recipe_app.ShoppingListIngredient;
import com.meri.recipe_app.database.RecipeDatabase;

import java.util.ArrayList;

public class IngredientDisplayFragment extends Fragment {

    RecipeDatabase db;

    ListView listViewIngredient;
    ArrayList<String> shoppingList = new ArrayList<>();

    public IngredientDisplayFragment(){}
    public static IngredientDisplayFragment newInstance(ArrayList<String> ingredients){
        IngredientDisplayFragment ingredientDisplayFragment = new IngredientDisplayFragment();

        Bundle args = new Bundle();
        args.putStringArrayList("ingredients", ingredients);
        ingredientDisplayFragment.setArguments(args);

        return ingredientDisplayFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient_display, container, false);

        db = Room.databaseBuilder(getContext(), RecipeDatabase.class, "recipe-database5").build();

        ArrayList<String> list = getArguments().getStringArrayList("ingredients");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.row_array_item, list);
        listViewIngredient = (ListView) view.findViewById(R.id.listIngredientFragmentDisplay);
        listViewIngredient.setAdapter(adapter);

//        if u want to refresh ingredient list everytime the activity is being created
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                shoppingList = (ArrayList<String>) db.shoppingListIngredientDAO().getAllIngredients();
//            }
//        });
//        t.start();
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        listViewIngredient.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                String ingredient = list.get(i);

                // TODO: add to shopping list
                boolean inShoppingList = false;

                // check if item in shopping list
                for (String shoppingIngredient : shoppingList) {
                    if (shoppingIngredient.compareTo(ingredient) == 0){
                        inShoppingList = true;
                        break;
                    }
                }

                // remove if in shopping list
                if (inShoppingList) {
                    shoppingList.remove(ingredient);
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            db.shoppingListIngredientDAO().delete(list.get(i));
                        }
                    });
                    t.start();
                    view.setBackgroundResource(R.drawable.rounded_shape);
                    Log.d("cm", "removed from shopping list");
                }
                // add if not in shopping list
                else {
                    shoppingList.add(ingredient);
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ShoppingListIngredient ingredient = new ShoppingListIngredient();
                            ingredient.setIngredientName(list.get(i));
                            db.shoppingListIngredientDAO().insert(ingredient);
                        }
                    });
                    t.start();
                    view.setBackgroundResource(R.drawable.rounded_shape_done);
                    Log.d("cm", "added to shopping list");
                }

                return true;
            }
        });

        return view;
    }



}