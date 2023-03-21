package com.example.monpotager.actionslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monpotager.R;
import com.example.monpotager.models.Action;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ActionAdapter extends ListAdapter<Action, ActionViewHolder> {

        // FOR DATA

        private List<Action> actionList;

        // CONSTRUCTOR

        public ActionAdapter(@NonNull DiffUtil.ItemCallback<Action> diffCallback) {
            super(diffCallback);
            this.actionList = new ArrayList<>();
        }

        @Override
        public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return ActionViewHolder.create(parent);

        }

        @Override
        public void onBindViewHolder(ActionViewHolder holder, int position) {
            Action current = getItem(position);
            holder.bindActionType(current.getType());
            holder.bindActionParcelleId(Long.toString(current.getParcelleId()));
            holder.bindActionDate(current.getDate());
        }

    public static class ActionDiff extends DiffUtil.ItemCallback<Action> {

        @Override
        public boolean areItemsTheSame(@NonNull Action oldItem, @NonNull Action newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Action oldItem, @NonNull Action newItem) {
            return oldItem.getType().equals(newItem.getType());
        }
    }

}

