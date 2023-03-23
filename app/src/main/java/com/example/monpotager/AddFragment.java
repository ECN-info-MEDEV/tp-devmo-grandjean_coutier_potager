package com.example.monpotager;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    public static AddFragment newInstance() {
        return (new AddFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        Resources res = getResources();

        // Mettre le bon élément en première position pour qu'il puisse de cette manière apparaitre directement lorsqu'on ouvre le form
        String[] planetsArray = res.getStringArray(R.array.planets_array);
        List<String> planetList = new ArrayList<>(Arrays.asList(planetsArray));
        planetList.remove("Venus");
        planetList.add(0, "Venus");
        String[] finalPlanetsArray = planetList.toArray(new String[planetList.size()]);

        //Création et implémentation du spinner
        Spinner spinner = v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_spinner_item,
                finalPlanetsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        //on vérifie qu'on récupère bien la valueur rentrée

        Button button = (Button) v.findViewById(R.id.button_validate);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast toast = Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return v;
    }

}