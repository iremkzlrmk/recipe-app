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

public class MakingOfFragment extends Fragment {

    ListView listViewMakingOf;

    public MakingOfFragment(){}
    public static MakingOfFragment newInstance(ArrayList<String> makingOf){
        MakingOfFragment makingOfFragment = new MakingOfFragment();

        Bundle args = new Bundle();
        args.putStringArrayList("makingOf", makingOf);
        makingOfFragment.setArguments(args);

        return makingOfFragment;
    }

    public interface OnMakingOfEditedListener {
        void addMakingOf(String makingOf);
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
        Button btnAddMakingOf = (Button) view.findViewById(R.id.btnAddMakingOfFragment);

        ArrayList<String> list = getArguments().getStringArrayList("makingOf");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.row_making_of, list);
        listViewMakingOf = (ListView) view.findViewById(R.id.listMakingOfFragment);
        listViewMakingOf.setAdapter(adapter);

        btnAddMakingOf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String makingOf = txtMakingOf.getText().toString();

                adapter.notifyDataSetChanged();

                mListener.addMakingOf(makingOf);
            }
        });

        return view;
    }
}