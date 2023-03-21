package com.example.monpotager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return (new HomeFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void ValidateForm(View view) {
        Toast toast = Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_LONG);
        toast.show();
    }
}