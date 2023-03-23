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


/**
 * Class to manage the Fragment displayed by default/Home fragment
 * It contains a list of all Actions in a RecyclerView, from most recent to oldest
 */
public class HomeFragment extends Fragment {

    /**
     * Function to create a new Instance of Home Fragment
     * @return
     */
    public static HomeFragment newInstance() {
        return (new HomeFragment());
    }

    /**
     * ActionViewModel to display the actions
     */
    private ActionViewModel mActionViewModel;
    /**
     * RecyclerView where the Actions will be displayed
     */
    private RecyclerView recyclerView;

    /**
     * Function launched when the view is created.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        // Create RecyclerView
        recyclerView = layout.findViewById(R.id.recyclerview);
        final ActionAdapter adapter = new ActionAdapter(new ActionAdapter.ActionDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));

        // Create ActionViewModel and get all Actions
        mActionViewModel = new ViewModelProvider(this).get(ActionViewModel.class);
        mActionViewModel.getAllActions().observe(this.getViewLifecycleOwner(), actions -> {
            // Update the cached copy of the actions in the adapter.
            adapter.submitList(actions);
        });

        return layout;
    }

}

