package com.example.monpotager.actionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.monpotager.R;
import com.example.monpotager.models.Action;

public class ActionViewHolder extends RecyclerView.ViewHolder {

    private final TextView actionTypeView;
    private final TextView actionParcelleIdView;
    private final TextView actionDateView;


    public ActionViewHolder(View itemView) {

        super(itemView);

        actionTypeView = itemView.findViewById(R.id.action_type);
        actionParcelleIdView = itemView.findViewById(R.id.action_parcelle_id);
        actionDateView = itemView.findViewById(R.id.action_date);
    }

    public void bindActionType(String text) {
        actionTypeView.setText(text);
    }
    public void bindActionParcelleId(String text) {
        actionParcelleIdView.setText(text);
    }
    public void bindActionDate(String text) {
        actionDateView.setText(text);
    }

    static ActionViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ActionViewHolder(view);
    }

    public TextView getView(){
        return actionTypeView;
    }



}