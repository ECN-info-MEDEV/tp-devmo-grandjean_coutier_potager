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

@Dao
public interface ActionDao {

    @Query("SELECT * FROM action_table ORDER BY date DESC")
    LiveData<List<Action>> getAllActions();

    @Insert
    void insert(Action action);

    @Query("DELETE FROM action_table")
    void deleteAll();


}
