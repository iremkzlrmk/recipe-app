package com.meri.recipe_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class IngredientFragment extends Fragment {

    ListView listViewIngredient;

    public IngredientFragment(){}
    public static IngredientFragment newInstance(ArrayList<String> ingredients){
        IngredientFragment ingredientFragment = new IngredientFragment();

        Bundle args = new Bundle();
        args.putStringArrayList("ingredients", ingredients);
        ingredientFragment.setArguments(args);

        return ingredientFragment;
    }

    public interface OnIngredientsEditedListener {
        void addIngredient(String ingredient);
    }
    private OnIngredientsEditedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnIngredientsEditedListener) context;
        } catch (ClassCastException ex) {
            throw new RuntimeException(context.toString() + " must implement OnIngredientsEditedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient, container, false);

        EditText txtIngredient = (EditText) view.findViewById(R.id.txtIngredientFragment);
        Button btnAddIngredient = (Button) view.findViewById(R.id.btnAddIngredientFragment);

        ArrayList<String> list = getArguments().getStringArrayList("ingredients");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.row_making_of, list);
        listViewIngredient = (ListView) view.findViewById(R.id.listIngredientFragment);
        listViewIngredient.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btnAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ingredient = txtIngredient.getText().toString();

                adapter.notifyDataSetChanged();

                mListener.addIngredient(ingredient);
            }
        });

        return view;
    }
}