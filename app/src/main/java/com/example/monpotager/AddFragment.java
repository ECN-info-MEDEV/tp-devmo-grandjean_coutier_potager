package com.example.monpotager;

import static java.lang.Long.parseLong;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.monpotager.database.ActionViewModel;
import com.example.monpotager.models.Action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fragment which enable the creation of the form to add an action
 */
public class AddFragment extends Fragment {

    /**
     * ActionViewModel to display the parcelles
     */
    private ActionViewModel mActionViewModel;

    /**
     * List of all available Parcelles to display in the Spinner
     */
    private List<String> parcellesList = new ArrayList<>();

    /**
     * Spinner to display the list of all parcelles
     */
    private Spinner spinner;

    /**
     * EditText used to enter a date
     */
    private  EditText editDateView;


    /**
     * Action chosen by the user
     */
    private String actionchoisie = "";

    /**
     * Constructor
     * @return an AddFragment
     */
    public static AddFragment newInstance() {
        return (new AddFragment());
    }

    /**
     * Function launched when the View is created. It initializes all components for the form
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the View created
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        Context context = getActivity().getApplicationContext();

        //clearing selected action
        actionchoisie = "";

        // get today's date
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(now);

        // put today's date in the EditText used to choose a date
        editDateView= view.findViewById(R.id.editTextDate);
        editDateView.setText(date);
        
        // Create ActionViewModel and get all parcelles. Create a list of "Parcelle n°id".
        mActionViewModel = new ViewModelProvider(this).get(ActionViewModel.class);
        mActionViewModel.getAllParcelles().observe(this.getViewLifecycleOwner(), parcelles -> {
            AtomicInteger i= new AtomicInteger();
            parcelles.forEach(parcelle -> {
                String parcelleName = "Parcelle n°" + parcelle.getId();
                parcellesList.add(parcelleName);
                i.getAndIncrement();
        });

        //Creates and implements the spinner to choose a Parcelle
        String[] finalParcelleArray = parcellesList.toArray(new String[parcellesList.size()]);
        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(),
                R.layout.spinner_item, finalParcelleArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        });

        // Implements function to validate an action, with the "Valider" button
        Button button = view.findViewById(R.id.button_validate);
        button.setOnClickListener(validateView -> {
            // Checking that an action is selected
            if (actionchoisie.isEmpty()){
                // Informing that the input is not correct
                Toast toast = Toast.makeText(context, "Vous devez choisir une action réalisée", Toast.LENGTH_LONG);
                toast.show();
            //Checking that a date is selected
            } else if (TextUtils.isEmpty(editDateView.getText())){
                // Informing that the input is not correct
                Toast toast = Toast.makeText(context, "Vous devez choisir une date", Toast.LENGTH_LONG);
                toast.show();
            } else {
                // Get the Spinner value
                String parcelleName = spinner.getSelectedItem().toString();
                String parcelleIdString = parcelleName.substring(parcelleName.length() - 1);
                Long parcelleId = parseLong(parcelleIdString);

                //Get the date value
                String actionDate= editDateView.getText().toString();

                Action action = new Action(parcelleId, actionchoisie, actionDate, "");
                mActionViewModel.insertA(action);

                // Inform that the action was registered in database
                Toast toast = Toast.makeText(context, "Action enregistrée", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        // Get buttons from view
        Button buttonarrosage = view.findViewById(R.id.button_arrosage);
        Button buttondesherbage = view.findViewById(R.id.button_désherbage);
        Button buttonremise0 = view.findViewById(R.id.button_remise0);
        Button buttonrecolte = view.findViewById(R.id.button_recolte);
        Button buttonplantation = view.findViewById(R.id.button_plantation);

        // Onclick Function for "Arroser" button
        buttonarrosage.setOnClickListener(arroserView -> {
            actionchoisie = "Arroser";
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.selectedButton), PorterDuff.Mode.MULTIPLY);
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        // Onclick Function for "Désherber" button
        buttondesherbage.setOnClickListener(desherberView -> {
            actionchoisie = "Désherber";
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.selectedButton), PorterDuff.Mode.MULTIPLY);
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        // Onclick Function for "Récolter" button
        buttonrecolte.setOnClickListener(recolterView -> {
            actionchoisie = "Récolter";
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.selectedButton), PorterDuff.Mode.MULTIPLY);
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        // Onclick Function for "Remise à 0" button
        buttonremise0.setOnClickListener(remise0View -> {
            actionchoisie = "Remise à 0";
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.selectedButton), PorterDuff.Mode.MULTIPLY);
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        // Onclick Function for "Planter" button
        buttonplantation.setOnClickListener(planterView -> {
            actionchoisie = "Planter";
            buttonplantation.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.selectedButton), PorterDuff.Mode.MULTIPLY);
            buttondesherbage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonrecolte.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonarrosage.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
            buttonremise0.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.backgroundButton), PorterDuff.Mode.MULTIPLY);
        });

        return view;
    }

}