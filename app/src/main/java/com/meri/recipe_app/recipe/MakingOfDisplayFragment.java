package com.meri.recipe_app.recipe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meri.recipe_app.DoneScreen;
import com.meri.recipe_app.MainActivity;
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

        ArrayList<String> doneSteps = new ArrayList<>();
//        doneSteps.addAll(list);

        listViewMakingOf.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                String makingOf = list.get(i);

                boolean inDoneStepsList = false;

                // check if item in steps list
                for (String step : doneSteps) {
                    if (step.compareTo(makingOf) == 0){
                        inDoneStepsList = true;
                        break;
                    }
                }

                // remove if in steps list
                if (inDoneStepsList) {
                    doneSteps.remove(makingOf);
                    view.setBackgroundResource(R.drawable.rounded_shape);
                    Log.d("cm", "removed from steps list");
                }
                // add if not in steps list
                else {
                    doneSteps.add(makingOf);
                    view.setBackgroundResource(R.drawable.rounded_shape_done);
                    Log.d("cm", "added to steps list");
                }

                if (doneSteps.size() == list.size()){
                    Intent intent = new Intent(getActivity(), DoneScreen.class);
                    startActivity(intent);
                    Log.d("cm", "done");
                }

                return true;
            }

        });

        return view;
    }

}