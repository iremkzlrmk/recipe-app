package com.meri.recipe_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChefRecipeAdapter extends BaseAdapter {

    List<ChefRecipe> recipes;
    private LayoutInflater inflater;

    public ChefRecipeAdapter(Activity activity, List<ChefRecipe> recipes){
        this.recipes = recipes;
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

        ChefRecipe recipe = recipes.get(position);
        View rowView;
        rowView = inflater.inflate(R.layout.row,null);
        TextView txtItemName = rowView.findViewById(R.id.txtItemName);
        ImageView imgRecipe = rowView.findViewById(R.id.imgItem);

        txtItemName.setText(recipe.getName());
        imgRecipe.setImageBitmap(recipe.getImage());

        return rowView;

    }
}
