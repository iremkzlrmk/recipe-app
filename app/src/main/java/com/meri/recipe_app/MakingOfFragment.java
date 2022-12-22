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

public class MakingOfFragment extends Fragment {

    public interface OnMakingOfEditedListener {
        void getEditedMakingOf(String makingOf);
    }
    private OnMakingOfEditedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnMakingOfEditedListener) context;
        } catch (ClassCastException ex) {
            throw new RuntimeException(context.toString() + " must implement OnMakingOfEditedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_making_of, container, false);

        EditText txtMakingOf = (EditText) view.findViewById(R.id.txtMakingOfFragment);
        txtMakingOf.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_DONE) {
                    mListener.getEditedMakingOf(txtMakingOf.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });

        return view;
    }
}