package com.example.monpotager.database;

import android.database.Cursor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.monpotager.models.Action;
import com.example.monpotager.models.Parcelle;

import java.util.List;

/**
 * Interface of the Dao for the Action entity
 */
@Dao
public interface ActionDao {

    /**
     * Function to get all Actions from database
     * @return a LiveData List of all Actions
     */
    @Query("SELECT * FROM action_table ORDER BY date DESC")
    LiveData<List<Action>> getAllActions();

    /**
     * Function to insert an Action into the database
     * @param action the Action to insert
     */
    @Insert
    void insert(Action action);

    /**
     * Function to delete all Actions from the database
     */
    @Query("DELETE FROM action_table")
    void deleteAll();


}
