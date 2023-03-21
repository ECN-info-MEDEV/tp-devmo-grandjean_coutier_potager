package com.example.monpotager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.monpotager.actionslist.ActionAdapter;
import com.example.monpotager.database.ActionViewModel;



public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return (new HomeFragment());
    }

    private ActionViewModel mActionViewModel;

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        // Add the following lines to create RecyclerView
        recyclerView = layout.findViewById(R.id.recyclerview);
        final ActionAdapter adapter = new ActionAdapter(new ActionAdapter.ActionDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        mActionViewModel = new ViewModelProvider(this).get(ActionViewModel.class);

        mActionViewModel.getAllActions().observe(this.getViewLifecycleOwner(), actions -> {
            // Update the cached copy of the actions in the adapter.
            adapter.submitList(actions);
        });

        return layout;
    }

    public void ValidateForm(View view) {
        Toast toast = Toast.makeText(getActivity(), R.string.toast_message, Toast.LENGTH_LONG);
        toast.show();
    }
}

