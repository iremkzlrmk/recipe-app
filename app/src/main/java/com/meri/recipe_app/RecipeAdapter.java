package com.meri.recipe_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecipeAdapter extends BaseAdapter {

    List<Recipe> recipes;
    private LayoutInflater inflater;

    public RecipeAdapter(Activity activity, List<Recipe> posts){
        this.recipes = posts;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Recipe recipe = recipes.get(position);
        View rowView;
        rowView = inflater.inflate(R.layout.row,null);
        TextView txtItemName = rowView.findViewById(R.id.txtItemName);
        ImageView imgRecipe = rowView.findViewById(R.id.imgRecipe);

        txtItemName.setText(recipe.getName());
        imgRecipe.setImageResource(R.drawable.ic_launcher_foreground);

        return rowView;

    }
}
