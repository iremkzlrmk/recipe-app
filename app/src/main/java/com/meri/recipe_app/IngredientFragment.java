package com.meri.recipe_app;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class IngredientFragment extends Fragment {

    public interface OnIngredientsEditedListener {
        void getEditedIngredients(String ingredients);
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

        EditText txtIngredients = (EditText) view.findViewById(R.id.txtIngredientFragment);
        txtIngredients.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_DONE) {
                    mListener.getEditedIngredients(txtIngredients.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });

        return view;
    }
}