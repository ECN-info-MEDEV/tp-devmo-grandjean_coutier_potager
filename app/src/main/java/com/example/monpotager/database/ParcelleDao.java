package com.example.monpotager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.monpotager.models.Parcelle;

import java.util.List;

@Dao
public interface ParcelleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Parcelle parcelle);

    @Query("SELECT * FROM parcelle_table WHERE id = :parcelleId")
    LiveData<Parcelle> getParcelle(long parcelleId);

    @Query("SELECT * FROM parcelle_table")
    LiveData<List<Parcelle>> getAllParcelles();

    @Query("DELETE FROM parcelle_table")
    void deleteAll();

}
