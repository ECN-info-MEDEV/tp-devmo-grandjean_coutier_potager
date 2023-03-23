package com.example.monpotager;

import static java.lang.Long.parseLong;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.monpotager.database.ActionViewModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Fragment which enable the creation of the form to add an action
 */
public class AddFragment extends Fragment {
    public static AddFragment newInstance() {
        return (new AddFragment());
    }

    /**
     * ActionViewModel to display the parcelles
     */
    private ActionViewModel mActionViewModel;

    /**
     * List of all available Parcelles to display in the Spinner
     */
    private List<String> parcellesList;

    // attribut pour stocker l'action choisie
    private String actionchoisie = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);
        Resources res = getResources();
        Context context = getActivity().getApplicationContext();
        
        // Create ActionViewModel and get all Actions
        mActionViewModel = new ViewModelProvider(this).get(ActionViewModel.class);
        mActionViewModel.getAllParcelles().observe(this.getViewLifecycleOwner(), parcelles -> {
            parcelles.forEach(parcelle -> {
                String parcelleName = "Parcelle n°" + String.valueOf(parcelle.getId());
                parcellesList.add(parcelleName);
            });

        });

        //mise à zéro de l'action
        actionchoisie = "";

        // récupérer la date du jour
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
        String date = formatter.format(now);

        // Mettre le bon élément en première position du spinner pour qu'il puisse de cette manière apparaitre directement lorsqu'on ouvre le form
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

        // Implémentation de la date du jour dans le form de date
        EditText dateView = v.findViewById(R.id.editTextDate);
        dateView.setText(date);

        // Implémentation de la fonction du bouton valider
        Button button = v.findViewById(R.id.button_validate);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // On vérifie qu'une action est bien sélectionnée.
                // Pour la parcelle ainsi que la date on a lors de l'affichage des valeurs par defaut donc pas besoin de les vérifier
                // passe bien dans la fonction et le toast fonctionne
                if (actionchoisie.isEmpty()){
                    // On indique que la saisie n'est pas valide
                    Toast toast = Toast.makeText(context, "Vous devez choisir une action réalisée", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    // Récupération de la valeur du Spinner
                    String parcelleName = spinner.getSelectedItem().toString();
                    String parcelleIdString = parcelleName.substring(parcelleName.length() - 1);
                    Long parcelleId = parseLong(parcelleIdString);
                    Log.d("Sélection", parcelleIdString);

                    // On indique que l'action a bien été enregistrée
                    Toast toast = Toast.makeText(context, "Action enregistrée", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        });

        // Récupération des différents boutons
        Button buttonarrosage = v.findViewById(R.id.button_arrosage);
        Button buttondesherbage = v.findViewById(R.id.button_désherbage);
        Button buttonremise0 = v.findViewById(R.id.button_remise0);
        Button buttonrecolte = v.findViewById(R.id.button_recolte);
        Button buttonplantation = v.findViewById(R.id.button_plantation);

        // Implémentation de la fonction du bouton arroser
        buttonarrosage.setOnClickListener(v15 -> {
            actionchoisie = "Arroser";
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        // Implémentation de la fonction du bouton désherber
        buttondesherbage.setOnClickListener(v14 -> {
            actionchoisie = "Désherber";
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        // Implémentation de la fonction du bouton récolter
        buttonrecolte.setOnClickListener(v13 -> {
            actionchoisie = "Récolter";
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        // Implémentation de la fonction du remise0
        buttonremise0.setOnClickListener(v12 -> {
            actionchoisie = "Remise à 0";
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        // Implémentation de la fonction du bouton planter
        buttonplantation.setOnClickListener(v1 -> {
            actionchoisie = "Planter";
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        return v;
    }

}