package com.example.monpotager.actionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.monpotager.R;
import com.example.monpotager.models.Action;

/**
 * Class to describe an item view within the Recycler View from Home Page
 */
public class ActionViewHolder extends RecyclerView.ViewHolder {

    /**
     * TextView displaying the type of the action
     */
    private final TextView actionTypeView;
    /**
     * TextView displaying the id of the parcel where the action was made
     */
    private final TextView actionParcelleIdView;
    /**
     * Textview displaying the date of the action
     */
    private final TextView actionDateView;

    /**
     * Constructor
     * @param itemView the view where the ViewHolder is
     */
    public ActionViewHolder(View itemView) {

        super(itemView);

        actionTypeView = itemView.findViewById(R.id.action_type);
        actionParcelleIdView = itemView.findViewById(R.id.action_parcelle_id);
        actionDateView = itemView.findViewById(R.id.action_date);
    }

    /**
     * Function to add a text to actionTypeView
     * @param text
     */
    public void bindActionType(String text) {
        actionTypeView.setText(text);
    }
    /**
     * Function to add a text to actionParcelleIdView
     * @param text
     */
    public void bindActionParcelleId(String text) {
        actionParcelleIdView.setText(text);
    }
    /**
     * Function to add a text to actionDateView
     * @param text
     */
    public void bindActionDate(String text) {
        actionDateView.setText(text);
    }

    /**
     * Function to create an ActionViewHolder
     * @param parent
     * @return
     */
    static ActionViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ActionViewHolder(view);
    }

}