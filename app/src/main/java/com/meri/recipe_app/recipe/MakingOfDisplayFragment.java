package com.meri.recipe_app.recipe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meri.recipe_app.R;

import java.util.ArrayList;

public class MakingOfDisplayFragment extends Fragment {

    ListView listViewMakingOf;

    public MakingOfDisplayFragment(){}
    public static MakingOfDisplayFragment newInstance(ArrayList<String> makingOf){
        MakingOfDisplayFragment makingOfDisplayFragment = new MakingOfDisplayFragment();

        Bundle args = new Bundle();
        args.putStringArrayList("makingOf", makingOf);
        makingOfDisplayFragment.setArguments(args);

        return makingOfDisplayFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_making_of_display, container, false);

        ArrayList<String> list = getArguments().getStringArrayList("makingOf");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.row_array_item, list);
        listViewMakingOf = (ListView) view.findViewById(R.id.listMakingOfFragmentDisplay);
        listViewMakingOf.setAdapter(adapter);

        ArrayList<String> shoppingList = new ArrayList<>();

        listViewMakingOf.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                String makingOf = list.get(i);

                // TODO: add to shopping list
                boolean inShoppingList = false;

                // check if item in shopping list
                for (String shoppingIngredient : shoppingList) {
                    if (shoppingIngredient.compareTo(makingOf) == 0){
                        inShoppingList = true;
                        break;
                    }
                }

                // remove if in shopping list
                if (inShoppingList) {
                    shoppingList.remove(makingOf);

                    Log.d("cm", "removed from shopping list");
                }
                // add if not in shopping list
                else {
                    shoppingList.add(makingOf);

                    Log.d("cm", "added to shopping list");
                }

                return true;
            }
        });






        return view;
    }

}