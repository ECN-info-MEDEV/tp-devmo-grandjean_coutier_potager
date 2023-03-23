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

/**
 * Class to present a List Data of Actions in a Recycler View, on the Home Page
 */
public class ActionAdapter extends ListAdapter<Action, ActionViewHolder> {

    /**
     * List of Actions to display
     */
    private List<Action> actionList;

    /**
     * Constructor
     * @param diffCallback callback to compare two item Actions
     */
    public ActionAdapter(@NonNull DiffUtil.ItemCallback<Action> diffCallback) {
        super(diffCallback);
        this.actionList = new ArrayList<>();
    }

    /**
     * Function launched when the ActionViewHolder is created
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the created ActionViewHolder
     */
    @Override
        public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return ActionViewHolder.create(parent);

        }

    /**
     * Function to bind an item of the Adapter to its right content
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
        public void onBindViewHolder(ActionViewHolder holder, int position) {
            Action current = getItem(position);
            holder.bindActionType(current.getType());
            holder.bindActionParcelleId(Long.toString(current.getParcelleId()));
            holder.bindActionDate(current.getDate());
        }

    /**
     * Function to compare two items and their content
     */
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

