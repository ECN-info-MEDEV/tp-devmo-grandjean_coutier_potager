package com.example.monpotager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.monpotager.models.Parcelle;

import java.util.List;

/**
 * Interface of the Dao for the Parcelle entity
 */
@Dao
public interface ParcelleDao {

    /**
     * Function to get all Parcelles from database
     * @return a LiveData List of all Parcemmes
     */
    @Query("SELECT * FROM parcelle_table")
    LiveData<List<Parcelle>> getAllParcelles();

    /**
     * Function to get a Parcelle from database
     * @param parcelleId the id of the Parcelle to get
     * @return the Parcelle
     */
    @Query("SELECT * FROM parcelle_table WHERE id = :parcelleId")
    LiveData<Parcelle> getParcelle(long parcelleId);

    /**
     * Function to insert a Parcelle in the database
     * @param parcelle the Parcelle to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Parcelle parcelle);

    /**
     * Function to delete all Parcelles from database
     */
    @Query("DELETE FROM parcelle_table")
    void deleteAll();

}
