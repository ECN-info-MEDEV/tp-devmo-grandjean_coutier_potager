package com.example.monpotager;

import android.content.Intent;
import android.os.Bundle;

import java.io.Console;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {
    String defaultTextForSpinner = "Mercury";

    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.planets_array, android.R.layout.simple_spinner_item);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("blblab", "4");
        setContentView(R.layout.activity_form);
        Log.d("blblab", "coucou");
        Intent intent = getIntent();
        Log.d("blblab", "passé");
        Log.d("blblab", intent.toString());

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        /* On definit une présentation du spinner quand il est déroulé */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(defaultTextForSpinner));
    }
}

